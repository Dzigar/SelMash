package com.selfmash.service;

import java.util.List;

import com.selfmash.dao.NotificationDAO;
import com.selfmash.model.Notification;

public interface NotificationService {

    void saveNotification(Notification notification);

    void removeNotificationById(long idNotification);

    public List<Notification> getNotificationsByUserId(long userId);
}
