package com.selfmash.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.NotificationDAO;
import com.selfmash.model.Notification;
import com.selfmash.service.NotificationService;

@Service("NotificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public void saveNotification(Notification notification) {
        notificationDAO.saveNotification(notification);
    }

    @Override
    public void removeNotificationById(long idNotification) {
        notificationDAO.removeNotificationById(idNotification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(long userId) {
        return notificationDAO.getNotificationsByUserId(userId);
    }

    @Override
    public void setNotificationIsReview(long notificationId) {
        Notification notification = notificationDAO
                .getNotificationById(notificationId);
        notification.setReview(true);
        notificationDAO.updateNotification(notification);
    }

}
