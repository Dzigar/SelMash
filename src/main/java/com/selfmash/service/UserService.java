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
    User getUserByLogin(String login);

    /**
     * Get user by id.
     * 
     * @param id
     *            User id
     * @return object User class
     */
    User getUserById(long id);

    /**
     * 
     * @return
     */
    List<User> getAll(long userId);

    /**
     * Save User in DB.
     * 
     * @param user
     *            - object User class
     */
    void addUser(User user);

    /**
     * Update changed User.
     * 
     * @param user
     *            - object User
     */
    void updateUser(User user);

    /**
     * Add new admirer.
     * 
     * @param idFirstUser
     *            - id user who adds
     * @param idSecondUser
     *            - id user which adds
     */
    void subscribe(long idFirstUser, long idSecondUser);

    /**
     * 
     * @param followerId
     * @param admirerId
     */
    void unsubscribe(long followerId, long admirerId);

    /**
     * 
     * @param userLogin
     * @param photoId
     */
    void setProfilePhoto(String userLogin, long photoId);

    /**
     * 
     * @param userId
     * @return
     */
    List<User> getFollowing(long userId);

    /**
     * 
     * @param userId
     * @return list of admirers users.
     */
    List<User> getAdmirers(long userId);

    /**
     * 
     * @param userId
     * @return list of recommended users.
     */
    List<User> getRecommended(long userId);

    /**
     * 
     * @param userId
     */
    void removeProfilePhoto(long userId);
}
