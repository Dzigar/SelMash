package com.selfmash.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
@RequestMapping("/{login}/photo")
public class UserPhotoController {

	@Resource(name = "photoServiceImpl")
	private PhotoService photoService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "estimationServiceImpl")
	private EstimationService estimationService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String showUserSelectedPhoto(@PathVariable long id,
			@PathVariable String login, HttpServletRequest request,
			ModelMap model) {
		model.addAttribute("photo", photoService.getPhotoById(id));
		model.addAttribute("login", login);
		model.addAttribute("userId", userService.getUserId(login));
		model.addAttribute("Estimations",
				estimationService.getEstimationsByPhotoId(id));
		return "user_photo_page";
	}

	/**
	 * Upload user's photo
	 * 
	 * @param file
	 * @param model
	 * @param principal
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("image") MultipartFile file,
			Model model, Principal principal) throws IOException {
		Photo photo = new Photo();
		photo.setName(file.getOriginalFilename());
		photo.setAccountPhoto(false);
		photo.setDateUpload(new Date());
		User user = userService.getUser(principal.getName());
		photo.setUser(user);

		if (!file.isEmpty()) {
			File filePhoto = new File(Path.PHOTO_PATH + "/user" + user.getId());
			if (!filePhoto.exists()) {
				filePhoto.mkdir(); // create directory if it not exists
			}
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(Path.PHOTO_PATH + "/user"
								+ user.getId() + "/"
								+ file.getOriginalFilename())));
				stream.write(bytes); // write file to user directory
				stream.close();
				photoService.addphoto(photo); // save photo in DB
			} catch (Exception e) {
				logger.info(e.getLocalizedMessage());
				model.addAttribute("message", "You failed to upload photo");
			}
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
				Set<Photo> set = user.getPreferences();
				set.add(setAverageRating(id));
				user.setPreferences(set);
				userService.updateUser(user);
			}
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}
		return "redirect:" + id;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String deletePhoto(@PathVariable long id, Principal principal) {
		try {

			new FileDeleteHandler().deleteFilePhoto(
					photoService.getPhotoById(id).getName(),
					userService.getUserId(principal.getName()));
			photoService.deletePhoto(id);
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}
		return "redirect:/" + principal.getName();
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
