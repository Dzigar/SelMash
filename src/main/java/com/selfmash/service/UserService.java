package com.selfmash.service;

import java.util.List;

import com.selfmash.model.User;

/**
 * @author Dzigar.
 * 
 */
public interface UserService {

    /**
     * Get user by login.
     * 
     * @param login
     *            - User login.
     * @return object User class.
     */
    User getUser(String login);

    /**
     * Get user by id.
     * 
     * @param id
     *            User id
     * @return object User class
     */
    User getUserById(long id);

    /**
     * Save User in DB.
     * 
     * @param user
     *            - object User class
     * @return true if User saved successfully and return false id not.
     */
    boolean addUser(User user);

    /**
     * Get all users who have registered in service.
     * 
     * @return User list
     */
    List<User> getUserList();

    /**
     * Get User id.
     * 
     * @param login
     *            - User login
     * @return id User
     */
    long getUserId(String login);

    /**
     * Update changed User.
     * 
     * @param user
     *            - object User
     */
    boolean updateUser(User user);

    /**
     * 
     * @param userId
     *            - User id
     * @param photoId
     *            - Photo id
     * @return true or false
     */
    boolean containsPreferencesPhoto(long userId, long photoId);

    /**
     * Get how many days a user is registered on the service.
     * 
     * @param login
     *            - User login
     * @return number of days in service.
     */
    int getDaysOnline(String login);

    /**
     * Add User to friends list.
     * 
     * @param idFirstUser
     *            - id user who adds
     * @param idSecondUser
     *            - id user which adds
     */
    boolean addFriend(long idFirstUser, long idSecondUser);

    /**
     * Confirm friendship between users.
     * 
     * @param userId
     * @param friendId
     */
    boolean confirmFriendship(long userId, long friendId, long notificationId);

    /**
     * Get friends list by user id.
     * 
     * @param userId
     *            - User id
     * @return list User
     */
    List<User> getFriendsList(long userId);
}
