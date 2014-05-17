package com.selfmash.controller;

import java.security.Principal;
import java.util.ArrayList;
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
@RequestMapping(value = "/")
public class UserPageController {

	@Resource(name = "photoServiceImpl")
	private PhotoService photoService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@RequestMapping(value = "{login}", method = RequestMethod.GET)
	public String showUserPage(@PathVariable String login, ModelMap model,
			Principal principal) {
		try {
			User user = userService.getUser(login);
			List<User> friendsList = userService.getFriendsList(user.getId());
			model.addAttribute("user", user);
			model.addAttribute("friendList", friendsList);
			if (isFriends(principal.getName(), friendsList)) {
				model.addAttribute("isFriends", true);
			}
			model.addAttribute("photoRows", createUserPhotoCollection(login));
			model.addAttribute("daysonline", userService.getDaysOnline(login));
			model.addAttribute("profilePhoto", photoService
					.getAccoutPhoto(user).getTitle());
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return "user_page";
	}

	@RequestMapping(value = "/addfriend", method = RequestMethod.POST)
	public String addFriend(HttpServletRequest request, Principal principal) {
		try {
			userService.addFriend(userService.getUserId(principal.getName()),
					Long.parseLong(request.getParameter("user")));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return "redirect:/" + request.getParameter("login");
	}

	private boolean isFriends(String login, List<User> friendsList) {
		for (User user : friendsList) {
			if (user.getLogin().equals(login))
				return true;
		}
		return false;
	}

	// Creating a collection of photo
	private List<List<Photo>> createUserPhotoCollection(String login) {
		List<Photo> photos = photoService.getUserPhotos(userService
				.getUser(login));
		List<List<Photo>> photoColection = new ArrayList<List<Photo>>();
		int i = 0;
		while (i < photos.size()) {
			List<Photo> array = new ArrayList<Photo>();
			for (int j = 0; j < 2; j++) {
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
}
