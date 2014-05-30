package com.selfmash.beans;

import org.springframework.beans.factory.annotation.Autowired;

import com.selfmash.beans.enums.NotificationBody;
import com.selfmash.model.Notification;
import com.selfmash.service.UserService;

public class NotificationBean {

    @Autowired
    private UserService userService;

    public Notification addFollow(long idAdmirer, long idReceiver) {
        return new Notification(userService.getUserById(idAdmirer),
                userService.getUserById(idReceiver),
                NotificationBody.NEW_ADMIRER);
    }
}
