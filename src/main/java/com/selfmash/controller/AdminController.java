package com.selfmash.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.service.PhotoService;
import com.selfmash.service.UserService;

@Controller
public class AdminController {

	@Resource(name = "photoServiceImpl")
	private PhotoService photoService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String showAdminPage() {
		return "admin_page";
	}

	@RequestMapping(value = "/admin/sendRequest1", method = RequestMethod.POST)
	public String performFirstRequest(@PathVariable int dayFrom,
			@PathVariable int monthFrom, @PathVariable int yearFrom,
			@PathVariable int dayTo, @PathVariable int monthTo,
			@PathVariable int yearTo) {

		logger.info(dayFrom);
		logger.info(monthFrom);
		logger.info(yearFrom);

		return "admin_page";
	}

}
