package com.selfmash.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.selfmash.dao.MeetDAO;
import com.selfmash.model.Meet;
import com.selfmash.model.User;

@Repository
public class MeetDAOImpl implements MeetDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(getClass());

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void createMeet(Meet meet) {
        getCurrentSession().save(meet);
    }

    @Override
    public Meet getMeetByUsersId(User userFrom, User userTo) {
        return (Meet) getCurrentSession()
                .createQuery(
                        "from Meet m where m.userFrom = :userFrom and m.userTo = :userTo")
                .setParameter("userFrom", userFrom)
                .setParameter("userTo", userTo).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Meet> getMeetToFromUser(User userTo) {
        return getCurrentSession()
                .createQuery("from Meet m where m.userTo = :userTo")
                .setParameter("userTo", userTo).list();
    }

    @Override
    public Meet getMeetById(long meetId) {
        return (Meet) getCurrentSession()
                .createQuery("from Meet m where m.id = :id")
                .setParameter("id", meetId).uniqueResult();
    }

    @Override
    public void updateMeet(Meet meet) {
        getCurrentSession().update(meet);
    }
}
