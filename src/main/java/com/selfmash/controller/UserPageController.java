package com.selfmash.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.model.User;
import com.selfmash.service.PhotoService;
import com.selfmash.service.UserService;

/**
 * @author Dzigar.
 */
@Controller
@RequestMapping(value = "/")
public class UserPageController {

    /**
     * Photo Service object.
     */
    @Resource(name = "photoServiceImpl")
    private PhotoService photoService;

    /**
     * UserService.
     */
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * Create logger.
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * 
     * @param login
     *            - User login
     * @param model
     *            - link to Model object
     * @param principal
     *            - link to Principal object
     * @return user_page -
     */
    @RequestMapping(value = "{login}", method = RequestMethod.GET)
    public String showUserPage(@PathVariable String login, ModelMap model,
            HttpServletRequest request, Principal principal) throws Exception {
        User user = userService.getUserByLogin(login);
        if (user != null) {
            try {
                request.getSession().setAttribute("userLogin", login);
                model.addAttribute("user", user);
                model.addAttribute("photos", photoService
                        .getUserPhotos(userService.getUserByLogin(login)));
                model.addAttribute("userFollowers",
                        userService.getFollowing(user.getId()));
                model.addAttribute("userAdmirers",
                        userService.getAdmirers(user.getId()));

            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
            }
            if (principal.getName().equals(login)) {
                return "profile_page";
            } else
                return "user_page";
        } else
            throw new Exception();
    }

    /**
     * 
     * @param request
     *            -link to HttpServletRequest object
     * @param principal
     *            - link to Principal object
     * @return method redirect to user_page
     */
    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public String follow(HttpServletRequest request, Principal principal) {
        try {
            userService.subscribe(
                    userService.getUserByLogin(principal.getName()).getId(),
                    Long.parseLong(request.getParameter("userId")));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return "redirect:/" + request.getParameter("userLogin");
    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
    public String unsubscribe(HttpServletRequest request, Principal principal) {
        try {
            userService.unsubscribe(Long.parseLong(request
                    .getParameter("userId")),
                    userService.getUserByLogin(principal.getName()).getId());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return "redirect:/" + request.getParameter("userLogin");
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Principal principal,
            ModelMap map) {
        try {
            map.addAttribute(
                    "people",
                    userService.getAll(userService.getUserByLogin(
                            principal.getName()).getId()));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return "people";
    }

}
