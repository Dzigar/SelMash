package com.selfmash.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.beans.NotificationBean;
import com.selfmash.beans.PostBean;
import com.selfmash.dao.UserDAO;
import com.selfmash.model.User;
import com.selfmash.service.NotificationService;
import com.selfmash.service.PhotoService;
import com.selfmash.service.UserService;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PostBean postBean;

    @Autowired
    private NotificationBean notificationBean;

    @Autowired
    private PhotoService photoService;
    /**
     * Logger for UserServiceImpl class.
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        try {
            userDAO.updateUser(user);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public int getDaysOnline(String login) {
        return userDAO.getDaysOnline(login);
    }

    @Override
    public void subscribe(long userId, long admirerId) {
        try {
            userDAO.subscribe(userId, admirerId);
            // Create new notification
            notificationService.saveNotification(notificationBean.addFollow(
                    userId, admirerId));
            // Create post with subscription.
            postBean.addPost(userId, admirerId);
        } catch (Exception e) {
            logger.equals(e.getLocalizedMessage());
        }

    }

    @Override
    public void setProfilePhoto(String userLogin, long photoId) {
        User user = getUserByLogin(userLogin);
        user.setProfilePhoto(photoService.getPhotoById(photoId));
        updateUser(user);
    }

    @Override
    public List<User> getFollowing(long userId) {
        return userDAO.getFollowing(userId);
    }

    @Override
    public List<User> getAdmirers(long userId) {
        return userDAO.getAdmirers(userId);
    }

    @Override
    public void unsubscribe(long followerId, long admirerId) {
        userDAO.unsubscribe(followerId, admirerId);
    }

    @Override
    public void removeProfilePhoto(long userId) {
        userDAO.removeProfilePhoto(userId);
    }
}
