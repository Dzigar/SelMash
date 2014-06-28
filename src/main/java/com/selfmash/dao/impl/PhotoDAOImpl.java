package com.selfmash.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.PhotoDAO;
import com.selfmash.model.Photo;
import com.selfmash.model.User;
import com.selfmash.service.EstimationService;
import com.selfmash.service.UserService;
import com.selfmash.strings.PhotoQueries;

@Repository
public class PhotoDAOImpl implements PhotoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "estimationServiceImpl")
    private EstimationService estimationService;

    private Logger logger = Logger.getLogger(getClass().getName());

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void addphoto(Photo selfShot) {
        try {
            getCurrentSession().save(selfShot);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Photo> getUserPhotos(User user) {
        try {
            return getCurrentSession()
                    .createQuery(PhotoQueries.GET_USER_PHOTOS)
                    .setParameter("user", user).list();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Photo getPhotoById(long id) {
        return (Photo) getCurrentSession()
                .createQuery(PhotoQueries.GET_PHOTO_BY_ID)
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public void updatePhoto(Photo photo) {
        try {
            getCurrentSession().update(photo);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void mergePhoto(Photo photo) {
        try {
            getCurrentSession().merge(photo);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void deletePhoto(Photo photo) {
        try {
            estimationService.removeEstimationsByPhotoId(photo.getId());
            getCurrentSession().createQuery(PhotoQueries.DELETE_PHOTO_BY_ID)
                    .setParameter("photoId", photo.getId()).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

}
