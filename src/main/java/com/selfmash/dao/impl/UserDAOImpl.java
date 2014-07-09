package com.selfmash.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.UserDAO;
import com.selfmash.model.City;
import com.selfmash.model.User;
import com.selfmash.model.enums.Sex;
import com.selfmash.strings.UserQueries;

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
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            return (User) getCurrentSession()
                    .createQuery(UserQueries.GET_USER_BY_LOGIN)
                    .setParameter("login", login).uniqueResult();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public User getUserById(long id) {
        try {
            return (User) getCurrentSession()
                    .createQuery(UserQueries.GET_USER_BY_ID)
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            logger.equals(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        try {
            getCurrentSession().merge(user);
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
    }

    @Override
    public void subscribe(long admirerId, long followerId) {
        try {
            getCurrentSession().createSQLQuery(UserQueries.SUBSCRIBE)
                    .setLong("followerId", followerId)
                    .setLong("admirerId", admirerId).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getFollowing(long userId) {
        try {
            return getCurrentSession()
                    .createSQLQuery(UserQueries.SELECT_FOLLOWING)
                    .addEntity(User.class).setLong("userId", userId).list();
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAdmirers(long userId) {
        try {
            return getCurrentSession()
                    .createSQLQuery(UserQueries.SELECT_ADMIRERS)
                    .addEntity(User.class).setLong("userId", userId).list();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void unsubscribe(long followerId, long admirerId) {
        try {
            getCurrentSession().createSQLQuery(UserQueries.UNSUBSCRIBE)
                    .setLong("followerId", followerId)
                    .setLong("admirerId", admirerId).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeProfilePhoto(long userId) {
        try {
            getCurrentSession()
                    .createSQLQuery(UserQueries.REMOVE_PROFILE_PHOTO)
                    .setParameter("userId", userId).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll(long userId) {
        try {
            return getCurrentSession()
                    .createSQLQuery(UserQueries.GET_ALL_USERS)
                    .addEntity(User.class).setLong("userId", userId).list();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return new ArrayList<User>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getRecommended(long userId) {
        try {
            List<BigInteger> usersId = getCurrentSession()
                    .createSQLQuery(
                            "select u.id from user as u where u.id in "
                                    + "(select f.user_id from followers f "
                                    + "join user_role as ur on f.user_id = ur.user_id where f.admirer_id in "
                                    + "(select admirer_id from followers where admirer_id in "
                                    + "(select user_id from followers where admirer_id = :userId))) "
                                    + "and u.id != :userId order by u.rating desc")
                    .setParameter("userId", userId).list();

            return getCurrentSession()
                    .createSQLQuery(
                            "Select * from user u "
                                    + "left join user_role as ur on "
                                    + "ur.user_id = u.id "
                                    + "where u.id in (:usersId) order by u.rating limit 3")
                    .addEntity(User.class).setParameterList("usersId", usersId)
                    .list();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int getUserAge(String login) {
        return ((Double) getCurrentSession()
                .createSQLQuery(UserQueries.GET_USER_AGE)
                .setParameter("login", login).uniqueResult()).intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUsersByParams(int ageFrom, int ageTo, City city,
            Sex sex) {
        List<BigInteger> usersId = getCurrentSession()
                .createSQLQuery(
                        "SELECT u.id from user u where u.sex like :sex and u.city_id = :cityId and (DATE_FORMAT(NOW(), '%Y') - DATE_FORMAT(u.birthDate, '%Y')"
                                + " - (DATE_FORMAT(NOW(), '00-%m-%d')"
                                + " < DATE_FORMAT(u.birthDate, '00-%m-%d'))) "
                                + " between :from and :to")
                .setParameter("cityId", city.getId())
                .setParameter("sex", sex.name()).setParameter("from", ageFrom)
                .setParameter("to", ageTo).list();

        return getCurrentSession()
                .createSQLQuery(
                        "select  * from user u join user_role ur on ur.user_id = u.id where u.id in (:usersId)")
                .addEntity(User.class).setParameterList("usersId", usersId)
                .list();
    }
}
