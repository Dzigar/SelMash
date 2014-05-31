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
import com.selfmash.strings.Queries;

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
        getCurrentSession().save(selfShot);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Photo> getUserPhotos(User user) {
        try {
            List<Photo> list = getCurrentSession()
                    .createQuery("from Photo as p where p.user = :user")
                    .setParameter("user", user).list();
            return list;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Photo getPhotoById(long id) {
        return (Photo) getCurrentSession()
                .createQuery("from Photo as u where u.id = :id")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public void updatePhoto(Photo photo) {
        getCurrentSession().update(photo);
    }

    @Override
    public void deletePhoto(long id) {
        try {
            // estimationService.removeEstimationsByPhotoId(id);
            getCurrentSession().createQuery(Queries.QUERY_DELETE_PHOTO_BY_ID)
                    .setParameter("photoId", id).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }
}
