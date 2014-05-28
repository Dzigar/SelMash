package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.Notification;

public interface NotificationDAO {

    void saveNotification(Notification notification);

    void removeNotificationById(long idNotification);

    List<Notification> getNotificationsByUserId(long userId);

    Notification getNotificationById(long notificationId);
}
