package com.selfmash.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.UserDAO;
import com.selfmash.model.User;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	private Logger loger = Logger.getLogger(getClass().getName());

	@Override
	public User getUser(String login) {
		loger.debug("Get user via login");
		return userDAO.getUser(login);
	}

	@Override
	public void addUser(User user) {
		loger.debug("Added user " + user);
		userDAO.addUser(user);
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
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public boolean containsPreferencesPhoto(long userId, long photoId) {
		return userDAO.containsPreferencesPhoto(userId, photoId);
	}

}