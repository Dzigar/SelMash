package com.selfmash.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.selfmash.model.User;
import com.selfmash.service.MeetService;
import com.selfmash.service.UserService;

@Controller
public class MeetController {

    @Autowired
    private MeetService meetService;

    @Autowired
    private UserService userService;

    private User user;

    @RequestMapping(value = "/meets", method = RequestMethod.GET)
    public String meets(ModelMap map, Principal principal) {
        user = userService.getUserByLogin(principal.getName());
        map.addAttribute("meets", meetService.getMeetToFromUser(user.getId()));
        return "meets_page";
    }

    @RequestMapping(value = "/meet/{id}/accept")
    public ModelAndView acceptMeet(@PathVariable long meetId) {
        
        return new ModelAndView("meets_page");
    }
}
