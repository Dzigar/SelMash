package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.User;

public interface UserDAO {

    /**
     * Save user in DB.
     * 
     * @param user
     *            - object User entity
     */
    void addUser(User user);

    /**
     * 
     * @param login
     * @return user by user login
     */
    User getUserByLogin(String login);

    /**
     * 
     * @param id
     *            - id of user
     * @return user by user id
     */
    User getUserById(long id);

    /**
     * Update changed user entity.
     * 
     * @param user
     *            - object User entity
     * @return
     */
    void updateUser(User user);

    /**
     * 
     * @param userId
     * @param photoId
     * @return
     */
    boolean containsPreferencesPhoto(long userId, long photoId);

    /**
     * 
     * @param login
     * @return
     */
    int getDaysOnline(String login);

    /**
     * 
     * @param idUser
     * @param idAdmirer
     */
    void subscribe(long idUser, long idAdmirer);

    /**
     * 
     * @param idUser
     * @param idAdmirer
     */
    void unsubscribe(long idUser, long idAdmirer);

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
     */
    void removeProfilePhoto(long userId);
}
