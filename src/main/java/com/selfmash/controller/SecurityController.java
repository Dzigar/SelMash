package com.selfmash.controller;

import java.security.Principal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.model.User;
import com.selfmash.service.RoleService;
import com.selfmash.service.UserService;

@Controller
@RequestMapping("/")
public class SecurityController {

	private Logger logger = Logger.getLogger(getClass().getName());

	private final long USER_ROLE_ID = 2;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name = "passwordEncoder")
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String openIndexPage() {
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String welcomePage(Principal principal) {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		model.put("user", new User());
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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addUser(@Valid User user, BindingResult result,
			HttpServletRequest request) {
		try {
			if (result.hasErrors()) {
				logger.error("Error valigation registration : "
						+ result.getErrorCount());
				logger.error(result.getFieldError());
				return "access_page";
			} else {
				user.setRole(roleService.getRole(USER_ROLE_ID));
				String password = user.getPassword();
				user.setPassword(passwordEncoder.encodePassword(password,
						user.getLogin()));
				if (userService.addUser(user)) {
					// add registered user to authenticated list
					SecurityContextHolder
							.getContext()
							.setAuthentication(
									authenticationManager
											.authenticate(new UsernamePasswordAuthenticationToken(
													user.getLogin(), password)));
				} else {
					return "redirect:/login";
				}
			}
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
			return "access_page";
		}
		return "redirect:index";
	}
}
