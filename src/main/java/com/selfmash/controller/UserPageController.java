package com.selfmash.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.model.Photo;
import com.selfmash.model.User;
import com.selfmash.service.PhotoService;
import com.selfmash.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserPageController {

	@Resource(name = "photoServiceImpl")
	private PhotoService photoService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public String showUserPage(@PathVariable String login, ModelMap model,
			Principal principal, HttpServletRequest request) {
		User user = userService.getUser(login);
		model.addAttribute("userList", userService.getUserList());
		model.addAttribute("userId", user.getId());
		model.addAttribute("login", login);
		model.addAttribute("name", user.getName());
		model.addAttribute("lastname", user.getLastname());
		model.addAttribute("photoRows", createUserPhotoCollection(login));
		model.addAttribute("birthdate", user.getBirthDate());
		model.addAttribute("city", user.getCity());
		model.addAttribute("country", user.getCountry());
		model.addAttribute("daysonline", getDaysOnline(user.getDateReg()));
		model.addAttribute("language", user.getLanguage());
		try {
			model.addAttribute("profilePhoto",
					photoService.getAccoutPhoto(userService.getUser(login))
							.getName());
		} catch (Exception e) {
			logger.info(e);
		}

		return "user_page";
	}

	// Creating a collection of photo
	private List<List<Photo>> createUserPhotoCollection(String login) {
		List<Photo> photos = photoService.getUserPhotos(userService
				.getUser(login));
		List<List<Photo>> photoColection = new ArrayList<List<Photo>>();
		int i = 0;
		while (i < photos.size()) {
			List<Photo> array = new ArrayList<Photo>();
			for (int j = 0; j < 4; j++) {
				try {
					array.add(photos.get(i));
				} catch (Exception e) {
					logger.info(e.getLocalizedMessage());
					break;
				}
				i++;
			}
			photoColection.add(array);
		}
		return photoColection;
	}

	private int getDaysOnline(Date dateReg) {
		Date currentDate = new Date();
		long difference = currentDate.getTime() - dateReg.getTime();
		return (int) (difference / (24 * 60 * 60 * 1000));
	}
}
