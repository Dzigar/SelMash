package com.selfmash.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.selfmash.model.Meet;
import com.selfmash.model.User;
import com.selfmash.service.MeetService;
import com.selfmash.service.PostService;
import com.selfmash.service.RoleService;
import com.selfmash.service.StateService;
import com.selfmash.service.UserService;

/**
 * 
 * @author Dzigar.
 * 
 */

@Controller
@RequestMapping("/")
public class MainControlelr {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Autowired
    private PostService postService;

    @Autowired
    private StateService stateService;

    @Autowired
    private MeetService meetService;

    /**
     * 
     * @param request
     *            HttpServletRequest object
     * @return redirect to index.jsp
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final String openIndexPage(final HttpServletRequest request) {
        return "redirect:/index";
    }

    /**
     * 
     * @return index.jsp
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String welcomePage(Principal principal, ModelMap modelMap,
            HttpServletRequest request) {
        try {
            User user = userService.getUserByLogin(principal.getName());
            String city = request.getParameter("userCity");
            int ageFrom = 0;
            int ageTo = 0;
            if (city == null) {
                city = user.getCity().getName();
            }

            try {
                ageFrom = Integer.parseInt(request.getParameter("ageFrom"));
            } catch (Exception e) {
                ageFrom = userService.getUserAge(user.getLogin()) - 2;
            }
            try {
                ageTo = Integer.parseInt(request.getParameter("ageTo"));
            } catch (Exception e) {
                ageTo = userService.getUserAge(user.getLogin()) + 2;
            }

            modelMap.addAttribute("stateList", stateService.getAllStates());
            modelMap.addAttribute(
                    "users",
                    userService.getUsersByParams(ageFrom, ageTo, city,
                            user.getSex()));
            modelMap.addAttribute("ageFrom", ageFrom - 16);
            modelMap.addAttribute("ageTo", ageTo - 16);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return "index";
    }

    @RequestMapping(value = "/meet")
    public String meet(HttpServletRequest request, Principal principal) {
        User userFrom = userService.getUserByLogin(principal.getName());
        User userTo = userService.getUserById(Long.parseLong(request
                .getParameter("id")));
        meetService.createMeet(new Meet(userFrom, userTo));
        return "redirect:" + userTo.getLogin();
    }
}
