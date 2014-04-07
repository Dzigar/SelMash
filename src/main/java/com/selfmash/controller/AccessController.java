package com.selfmash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String openIndexPage() {
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String welcomePage() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "access_page";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "redirect:index";
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public String errorLogin() {
		return "login";
	}

}
