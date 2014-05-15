package com.selfmash.service;

import java.util.List;

import com.selfmash.model.User;

public interface UserService {

	public User getUser(String login);

	public boolean addUser(User user);

	public List<User> getUserList();

	public long getUserId(String login);

	public void updateUser(User user);

	public boolean containsPreferencesPhoto(long userId, long photoId);

	public int getDaysOnline(String login);

	public void addFriend(long idFirstUser, long idSecondUser);
	
	public List<User> getFriendsList(long userId);
}