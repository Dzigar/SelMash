package com.selfmash.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = Logger.getLogger(getClass());

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public void addUser(User user) {
		try {
			getCurrentSession().save(user);
		} catch (Exception e) {
			logger.info("Error in 'saveUser':" + e.getLocalizedMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList() {
		return getCurrentSession().createQuery("from User").list();
	}

	@Override
	public User getUser(String login) {
		return (User) getCurrentSession()
				.createQuery("from User as u where u.login = :login")
				.setParameter("login", login).uniqueResult();
	}

	@Override
	public long getUserId(String login) {
		return getUser(login).getId();
	}

	@Override
	public void updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}

	}

	@Override
	public boolean containsPreferencesPhoto(long userId, long photoId) {
		try {
			if (getCurrentSession()
					.createQuery(
							"From Photo p JOIN p.fans u WHERE p.id=:PHOTO_ID AND u.id=:USER_ID")
					.setParameter("PHOTO_ID", photoId)
					.setParameter("USER_ID", userId).list().size() > 0) {
				return true;
			}

		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}
		return false;
	}
}
