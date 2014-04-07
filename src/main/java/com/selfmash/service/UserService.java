package com.selfmash.service;

import java.util.List;

import com.selfmash.model.User;

public interface UserService {

	public User getUser(String login);

	public void addUser(User user);

	public List<User> getUserList();

	public long getUserId(String login);

	public void updateUser(User user);

	public boolean containsPreferencesPhoto(long userId, long photoId);
}