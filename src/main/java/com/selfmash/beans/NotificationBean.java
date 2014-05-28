package com.selfmash.beans;

import org.springframework.beans.factory.annotation.Autowired;

import com.selfmash.beans.enums.NotificationBody;
import com.selfmash.model.Notification;
import com.selfmash.model.User;
import com.selfmash.service.UserService;

public class NotificationBean {

    @Autowired
    private UserService userService;

    public Notification createAddFriendNotification(long idSender,
            long idReceiver) {

        User sender = userService.getUserById(idSender);
        User receiver = userService.getUserById(idReceiver);
        NotificationBody body = NotificationBody.ADD_TO_FRIENDS;
        body.setBody("notification.addToFriendList");
        return new Notification(sender, receiver, body);
    }
}
