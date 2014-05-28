package com.selfmash.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.beans.NotificationBean;
import com.selfmash.beans.PostBean;
import com.selfmash.beans.enums.ActionBody;
import com.selfmash.dao.NotificationDAO;
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
    private Logger loger = Logger.getLogger(getClass().getName());

    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    public boolean addUser(User user) {
        loger.debug("Added user " + user);
        return userDAO.addUser(user);
    }

    @Override
    public List<User> getUserList() {
        return userDAO.getUserList();
    }

    @Override
    public long getUserId(String login) {
        return userDAO.getUserId(login);
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean containsPreferencesPhoto(long userId, long photoId) {
        return userDAO.containsPreferencesPhoto(userId, photoId);
    }

    @Override
    public int getDaysOnline(String login) {
        return userDAO.getDaysOnline(login);
    }

    @Override
    public boolean addFriend(long userId, long friendId) {
        if (userDAO.addFriend(userId, friendId)) {
            notificationService.saveNotification(notificationBean
                    .createAddFriendNotification(userId, friendId));
            return true;
        }
        return false;
    }

    @Override
    public List<User> getFriendsList(long userId) {
        return userDAO.getFriendsList(userId);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public boolean confirmFriendship(long userId, long friendId,
            long notificationId) {
        if (userDAO.confirmFriendship(userId, friendId)) {
            // Remove notification
            notificationService.removeNotificationById(notificationId);
            // Add new Post
            postBean.addPost(userId, friendId, null, ActionBody.ADD_USER);
            return true;
        } else
            return false;
    }

    @Override
    public void setProfilePhoto(String userLogin, long photoId) {
        User user = getUser(userLogin);
        user.setProfilePhoto(photoService.getPhotoById(photoId));
        updateUser(user);
    }
}
