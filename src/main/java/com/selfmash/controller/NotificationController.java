package com.selfmash.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selfmash.service.NotificationService;
import com.selfmash.service.UserService;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @RequestMapping(method = RequestMethod.GET)
    public String notificationsUser(ModelMap map) {
        try {
            map.addAttribute("notifications", notificationService
                    .getNotificationsByUserId(userService.getUserByLogin(
                            SecurityContextHolder.getContext()
                                    .getAuthentication().getName()).getId()));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

        return "notifications_page";
    }

    @RequestMapping(value = "{id}/review", method = RequestMethod.POST)
    public String reviewNotification(HttpServletRequest request) {
        try {
            notificationService.setNotificationIsReview(Long.parseLong(request
                    .getParameter("notificationId").toString()));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return "redirect:/notifications";
    }
}
