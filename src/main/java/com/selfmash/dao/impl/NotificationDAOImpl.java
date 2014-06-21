package com.selfmash.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.NotificationDAO;
import com.selfmash.model.Notification;
import com.selfmash.model.Photo;
import com.selfmash.strings.NotificationQueries;

@Repository
public class NotificationDAOImpl implements NotificationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Logger for NotificationServiceImpl class.
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void saveNotification(Notification notification) {
        try {
            getCurrentSession().save(notification);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeNotificationById(long notificationId) {
        try {
            getCurrentSession().delete(getNotificationById(notificationId));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Notification> getNotificationsByUserId(long userId) {
        try {
            List<Notification> notifications = getCurrentSession()
                    .createSQLQuery(
                            NotificationQueries.GET_NOTIFICATIONS_BY_USER_ID)
                    .addEntity(Notification.class)
                    .setParameter("userId", userId).list();
            return notifications;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return new ArrayList<Notification>();
    }

    @Override
    public Notification getNotificationById(long notificationId) {
        try {
            return (Notification) getCurrentSession()
                    .createQuery(NotificationQueries.GET_NOTIFICATION_BY_ID)
                    .setParameter("notificationId", notificationId)
                    .uniqueResult();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void updateNotification(Notification notification) {
        try {
            getCurrentSession().merge(notification);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteNotificationByPhoto(Photo photo) {
        try {
            getCurrentSession()
                    .createQuery(
                            NotificationQueries.DELETE_NOTIFICATION_BY_PHOTO)
                    .setParameter("photo", photo).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }
}
