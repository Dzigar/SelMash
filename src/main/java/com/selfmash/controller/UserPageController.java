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
import org.springframework.web.bind.annotation.RequestParam;

import com.selfmash.model.Photo;
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
            HttpServletRequest request) {
        try {
            request.getSession().setAttribute("userLogin", login);
            User user = userService.getUser(login);
            model.addAttribute("user", user);
            model.addAttribute("photoRows", createUserPhotoCollection(login));
            model.addAttribute("profilePhoto",
                    photoService.getProfilePhoto(user).getTitle());

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return "user_page";
    }

    /**
     * 
     * @param request
     *            - - link to HttpServletRequest object
     * @param principal
     *            - link to Principal object
     * @return method redirect to user_page
     */
    @RequestMapping(value = "/addfriend", method = RequestMethod.POST)
    public final String addFriend(final HttpServletRequest request,
            final Principal principal) {
        try {
            userService.addFriend(userService.getUserId(principal.getName()),
                    Long.parseLong(request.getParameter("user")));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return "redirect:/" + request.getParameter("login");
    }

    @RequestMapping(value = "/confirmFriendship", method = RequestMethod.POST)
    public String confirmFriendShip(@RequestParam long friendId,
            @RequestParam long notId, Principal principal) {
        try {
            if (userService.confirmFriendship(friendId,
                    userService.getUserId(principal.getName()), notId)) {
                return "redirect:/notifications";
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return "redirect:/notifications";
    }

    /**
     * Creating a collection of photo.
     * 
     * @param login
     *            - User login
     * @return collection of photos
     */
    private List<List<Photo>> createUserPhotoCollection(final String login) {
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
