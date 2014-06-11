package com.selfmash.service;

import java.util.List;

import com.selfmash.model.Notification;
import com.selfmash.model.Photo;

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
     * @param notification
     */
    void updateNotification(Notification notification);

    /**
     * 
     * @param photoId
     */
    public void deleteNotificationByPhoto(Photo photo);
}
