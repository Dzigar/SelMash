package com.selfmash.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.selfmash.handler.FileDeleteHandler;
import com.selfmash.model.Estimation;
import com.selfmash.model.Photo;
import com.selfmash.model.User;
import com.selfmash.service.EstimationService;
import com.selfmash.service.PhotoService;
import com.selfmash.service.UserService;
import com.selfmash.strings.Path;

@Controller
@RequestMapping("/photo")
public class UserPhotoController {

    @Resource(name = "photoServiceImpl")
    private PhotoService photoService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "estimationServiceImpl")
    private EstimationService estimationService;

    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * 
     * @param id
     *            selected photo id
     * @param request
     * @param model
     * @return page with user selected photo.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showUserSelectedPhoto(@PathVariable long id,
            HttpServletRequest request, ModelMap model) {
        model.addAttribute("photo", photoService.getPhotoById(id));
        model.addAttribute("login", request.getParameter("value"));
        model.addAttribute("userId",
                userService.getUserId(request.getParameter("value")));
        // model.addAttribute("Estimations",
        // estimationService.getEstimationsByPhotoId(id));
        return "photo_page";
    }

    /**
     * This method save user photo in DB throw PhotoService. It create folder
     * with name of user name if it not exist and save in this folder loaded
     * file.
     * 
     * @param file
     *            - loaded photo file.
     * @param model
     * @param principal
     * @return redirect to user page
     * @throws IOException
     *             if file not found
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("image") MultipartFile file,
            Model model, Principal principal) throws IOException {
        try {
            User user = userService.getUser(principal.getName());
            Photo photo = new Photo(Long.toString(photoService.getLastId() + 1)
                    + "." + getExtensionFile(file), new Date(), user);
            photoService.addphoto(photo); // save photo in DB
            if (!file.isEmpty()) {
                File userFolder = new File(Path.PHOTO_PATH + "/"
                        + user.getLogin());
                if (!userFolder.exists()) {
                    userFolder.mkdir(); // create directory if it not exists
                }

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(Path.PHOTO_PATH + "/"
                                + user.getLogin() + "/" + photo.getTitle())));
                stream.write(bytes); // write file to user directory
                stream.close();
            }
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }

        return "redirect:/" + principal.getName();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public String estimatePhoto(@PathVariable long id, Principal principal,
            HttpServletRequest request) {
        try {
            User user = userService.getUser(principal.getName());
            if (!userService.containsPreferencesPhoto(user.getId(), id)) {
                estimationService.addEstimation(new Estimation(Float
                        .parseFloat(request.getParameter("estimation")
                                .toString()), id, user));
                // Set<Photo> set = user.getPreferences();
                // set.add(setAverageRating(id));
                // user.setPreferences(set);
                userService.updateUser(user);
            }
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return "redirect:" + id;
    }

    /**
     * This method delete photo in DB and remove file photo in server.
     * 
     * @param id
     *            - photo id.
     * @param principal
     * @return redirect to user page.
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deletePhoto(@PathVariable long id, Principal principal) {
        try {

            new FileDeleteHandler().deleteFilePhoto(
                    photoService.getPhotoById(id).getTitle(),
                    principal.getName());
            photoService.deletePhoto(id);
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return "redirect:/" + principal.getName();
    }

    /**
     * @param file
     *            - loaded user photo
     * @return extension of user loaded file.
     */
    private String getExtensionFile(MultipartFile file) {
        return file.getOriginalFilename().split("\\.")[1];
    }

    private Photo setAverageRating(long id) {
        float total = 0;
        Photo photo = photoService.getPhotoById(id);
        List<Estimation> estimations = estimationService
                .getEstimationsByPhotoId(id);
        for (int i = 0; i < estimations.size(); i++) {
            total = total + estimations.get(i).getEstimation();
        }

        photo.setAverageRating(total / estimations.size());
        photoService.updatePhoto(photo);
        return photo;
    }
}
