package com.selfmash.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.selfmash.dao.MessageDAO;
import com.selfmash.model.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(getClass().getName());

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveMessage(Message message) {
        try {
            getCurrentSession().save(message);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public List<Message> getMessagesByDialoogId(long dialogId) {
        return new ArrayList<Message>();
    }

}
