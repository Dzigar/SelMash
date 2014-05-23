package com.selfmash.dao.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.UserDAO;
import com.selfmash.model.User;
import com.selfmash.strings.Queries;

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
    public boolean addUser(User user) {
        try {
            getCurrentSession().save(user);
            return true;
        } catch (Exception e) {
            logger.info("Error in 'saveUser':" + e.getLocalizedMessage());
            return false;
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
                .createQuery(Queries.QUERY_GET_USER_BY_LOGIN)
                .setParameter("login", login).uniqueResult();
    }

    @Override
    public long getUserId(String login) {
        return getUser(login).getId();
    }

    @Override
    public boolean updateUser(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
            return true;
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return false;
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

    @Override
    public int getDaysOnline(String login) {
        try {
            String query = "SELECT DATEDIFF('"
                    + new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                    + "', u.dateReg) from user u where u.login = '" + login
                    + "'";
            return ((BigInteger) getCurrentSession().createSQLQuery(query)
                    .uniqueResult()).intValue();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public boolean addFriend(long userId, long friendId) {
        try {
            getCurrentSession().createSQLQuery(Queries.QUERY_ADD_FRIEND)
                    .setLong("userId", userId).setLong("friendId", friendId)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getFriendsList(long userId) {
        try {
            return getCurrentSession()
                    .createSQLQuery(Queries.QUERY_SELECT_FRIENDS)
                    .addEntity(User.class).setLong("userId", userId).list();
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return null;
    }

    /**
     * 
     */
    @Override
    public User getUserById(long id) {
        try {
            return (User) getCurrentSession()
                    .createQuery(Queries.QUERY_GET_USER_BY_ID)
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.equals(e.getLocalizedMessage());
        }
        return null;
    }
}
