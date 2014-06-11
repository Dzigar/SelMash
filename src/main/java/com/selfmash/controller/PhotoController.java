package com.selfmash.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.selfmash.beans.FileManagerBean;
import com.selfmash.beans.PostBean;
import com.selfmash.model.Estimation;
import com.selfmash.model.Notification;
import com.selfmash.model.Photo;
import com.selfmash.model.Post;
import com.selfmash.model.User;
import com.selfmash.service.EstimationService;
import com.selfmash.service.NotificationService;
import com.selfmash.service.PhotoService;
import com.selfmash.service.PostService;
import com.selfmash.service.UserService;
import com.selfmash.strings.Path;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Resource(name = "photoServiceImpl")
    private PhotoService photoService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Autowired
    private EstimationService estimationService;

    @Autowired
    private FileManagerBean fileManagerBean;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PostBean postBean;

    @Autowired
    private PostService postService;

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
            HttpServletRequest request, ModelMap model, Principal principal) {
        try {
            model.addAttribute("photo", photoService.getPhotoById(id));
            model.addAttribute("isAppreciate",
                    estimationService.isAppreciate(
                            userService.getUserByLogin(principal.getName())
                                    .getId(), id));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

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
            User user = userService.getUserByLogin(principal.getName());
            Photo photo = new Photo(file.getOriginalFilename(), new Date(),
                    user);
            photoService.addPhoto(user, photo); // save photo in DB
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

    /**
     * This method make selected photo is profile.
     * 
     * @param id
     *            - photo id
     * @return redirect to selected photo page.
     */
    @RequestMapping(value = "/makeProfile/{id}", method = RequestMethod.POST)
    public String makeProfile(@PathVariable long id, HttpServletRequest request) {
        userService.setProfilePhoto(
                request.getSession().getAttribute("userLogin").toString(), id);
        return "redirect:/photo/" + id;
    }

    @RequestMapping(value = "/appreciate/{id}", method = RequestMethod.POST)
    public String appreciatePhoto(@PathVariable long id, Principal principal,
            HttpServletRequest request) {
        try {
            Photo photo = photoService.getPhotoById(id);
            User user = userService.getUserByLogin(principal.getName());
            Estimation estimation = new Estimation(Float.parseFloat(request
                    .getParameter("estimation").toString()), null, user);
            estimationService.addEstimation(estimation); // save estimation

            // save post
            Post post = new Post(estimation.getUser(), estimation);
            postBean.addPost(post);

            // update photo with estimation and post
            photo.addEstimation(estimation);
            photo.addPost(post);
            photo.setAverageRating(getAverage(photo));
            photoService.updatePhoto(photo);
            postService.mergeWithEstimation(post.getId(), estimation.getId());

            try {
                Notification notification = new Notification(user, photo);
                notificationService.saveNotification(notification);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return "redirect:/photo/" + id;
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
            Photo photo = photoService.getPhotoById(id);
            photoService.deletePhoto(photo);
            fileManagerBean.deleteFilePhoto(photo.getTitle(),
                    principal.getName());
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return "redirect:/" + principal.getName();
    }

    /**
     * Calculation average rating photo by number estimations.
     * 
     * @param photo
     * @return average rating.
     */
    private float getAverage(Photo photo) {
        float total = 0;
        Set<Estimation> estimations = photo.getEstimations();
        for (Estimation estimation : estimations) {
            total = total + estimation.getEstimation();
        }
        return total / estimations.size();
    }

}
