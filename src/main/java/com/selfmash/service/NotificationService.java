package com.selfmash.service;

import java.util.List;

import com.selfmash.model.Notification;

public interface NotificationService {

    /**
     * 
     * @param notification
     */
    void saveNotification(Notification notification);

    /**
     * 
     * @param idNotification
     */
    void removeNotificationById(long idNotification);

    /**
     * 
     * @param userId
     * @return
     */
    List<Notification> getNotificationsByUserId(long userId);

    /**
     * 
     * @param userId
     * @return
     */
    int getNotificationsCountByUserId(long userId);

    /**
     * 
     * @param notificationId
     */
    void setNotificationIsReview(long notificationId);

    /**
     * 
     * @param notification
     */
    void updateNotification(Notification notification);
}
