package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.Notification;
import com.selfmash.model.Photo;

public interface NotificationDAO {

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
     * @param notificationId
     * @return
     */
    Notification getNotificationById(long notificationId);

    /**
     * 
     * @param notification
     */
    void updateNotification(Notification notification);

    /**
     * 
     * @param photoId
     */
    void deleteNotificationByPhoto(Photo photo);
}
