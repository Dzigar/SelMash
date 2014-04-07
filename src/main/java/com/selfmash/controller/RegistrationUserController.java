package com.selfmash.controller;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.checking.RegistrationUserValidation;
import com.selfmash.model.User;
import com.selfmash.service.RoleService;
import com.selfmash.service.UserService;

@Controller
public class RegistrationUserController {

	private Logger logger = Logger.getLogger(getClass().getName());

	private final long USER_ROLE_ID = 2;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "roleService")
	private RoleService roleService;

	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String addUser(@Valid RegistrationUserValidation userValidation,
			BindingResult result,
			Map<String, RegistrationUserValidation> model,
			HttpServletRequest request) throws ParseException {
		if (result.hasErrors()) {
			logger.info("Error valigation registration : "
					+ result.getErrorCount());
			logger.info(result.getFieldError());
			return "reg";
		} else {
			User user = new User();
			user.setName(userValidation.getName());
			user.setLastname(userValidation.getLastname());
			user.setBirthDate(userValidation.getBirthDate());
			user.setLogin(userValidation.getLogin());
			user.setPassword(userValidation.getPassword());
			user.setEmail(userValidation.getEmail());
			user.setRole(roleService.getRole(USER_ROLE_ID));
			userService.addUser(user);

			// add registered user to authenticated list
			try {

				SecurityContextHolder
						.getContext()
						.setAuthentication(
								authenticationManager
										.authenticate(new UsernamePasswordAuthenticationToken(
												user.getLogin(), user
														.getPassword())));
			} catch (Exception e) {
				logger.info(e.getLocalizedMessage());
			}
		}
		model.put("validationForm", userValidation);
		return "index";
	}
}
