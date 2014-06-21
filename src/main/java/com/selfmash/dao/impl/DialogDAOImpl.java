package com.selfmash.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.selfmash.dao.DialogDAO;
import com.selfmash.model.Dialog;
import com.selfmash.model.User;
import com.selfmash.strings.DialogQueries;

@Repository
public class DialogDAOImpl implements DialogDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(getClass().getName());

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveDialog(Dialog dialog) {
        try {
            getCurrentSession().save(dialog);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public Dialog getDialogByUsers(User user1, User user2) {
        try {
            Query query = getCurrentSession()
                    .createSQLQuery(DialogQueries.GET_DIALOG_BY_USER_ID)
                    .addEntity(Dialog.class)
                    .setParameter("user1Id", user1.getId())
                    .setParameter("user2Id", user2.getId());
            Dialog dialog = (Dialog) query.uniqueResult();
            return dialog;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void updateDialog(Dialog dialog) {
        try {

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

}
