package com.selfmash.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.selfmash.dao.EstimationDAO;
import com.selfmash.dao.PostDAO;
import com.selfmash.model.Estimation;
import com.selfmash.model.User;
import com.selfmash.strings.Queries;

@Repository
public class EstimationDAOImpl implements EstimationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PostDAO postDAO;

    private Logger logger = Logger.getLogger(getClass().getName());

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addEstimation(Estimation estimation) {
        try {
            getCurrentSession().merge(estimation);
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeEstimationsByPhotoId(long id) {
        try {
            List<Estimation> estimations = getEstimationsByPhotoId(id);
            for (Estimation estimation : estimations) {
                postDAO.deletePost(estimation.getPost());
                estimation.setPost(null);
                estimation.setUser(null);
                estimation.setPhoto(null);
                getCurrentSession()
                        .createQuery("delete Estimation where id = :estId")
                        .setParameter("estId", estimation.getId())
                        .executeUpdate();
            }
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Estimation> getEstimationsByPhotoId(long id) {
        try {
            return getCurrentSession()
                    .createSQLQuery(Queries.QUERY_GET_ESTIMATION_BY_PHOTO_ID)
                    .addEntity(Estimation.class).setParameter("photoId", id)
                    .list();

        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return null;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<User> getAdmirersByPhotoId(long photoId) {
        try {
            return getCurrentSession()
                    .createSQLQuery(Queries.QUERY_GET_EDMIRERS_BY_PHOTO_ID)
                    .addEntity(User.class).setParameter("photoId", photoId)
                    .list();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return new ArrayList<User>();
    }

    @Override
    public boolean isAppreciated(long userId, long photoId) {
        try {
            return (Boolean) getCurrentSession()
                    .createSQLQuery(Queries.QUERY_CHECH_FOR_APPRECIATE)
                    .addScalar("containce", Hibernate.BOOLEAN)
                    .setParameter("userId", userId)
                    .setParameter("photoId", photoId).uniqueResult();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return false;
    }
}
