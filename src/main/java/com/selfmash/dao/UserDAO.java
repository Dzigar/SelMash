package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.User;

public interface UserDAO {

    boolean addUser(User user);

    User getUser(String login);

    User getUserById(long id);
    
    List<User> getUserList();

    long getUserId(String login);

    boolean updateUser(User user);

    boolean containsPreferencesPhoto(long userId, long photoId);

    int getDaysOnline(String login);

    boolean addFriend(long idFirstUser, long idSecondUser);

    List<User> getFriendsList(long userId);
}
