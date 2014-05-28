package com.selfmash.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.PhotoDAO;
import com.selfmash.model.Photo;
import com.selfmash.model.User;
import com.selfmash.service.PhotoService;
import com.selfmash.service.UserService;

/**
 * 
 * @author Dzigar
 * 
 */
@Service("photoServiceImpl")
@Transactional
public class PhotoServiceImpl implements PhotoService {

    /**
     * 
     */
    @Autowired
    private PhotoDAO photoDAO;

    @Autowired
    private UserService userService;

    /**
     * Logger for PhotoServiceImpl class.
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * @param photo
     *            - object Photo for saving
     */
    @Override
    public final void addphoto(final Photo photo) {
        photoDAO.addphoto(photo);
        logger.info("Upload new photo:" + photo.getTitle());
    }

    /**
     * @param user
     *            - object User
     * @return list of photo User
     */
    @Override
    public final List<Photo> getUserPhotos(final User user) {
        return photoDAO.getUserPhotos(user);
    }

    /**
     * @param id
     *            - user id
     * @return list of photo by user id
     */
    @Override
    public final Photo getPhotoById(final long id) {
        return photoDAO.getPhotoById(id);
    }

    /**
     * @param photo
     *            - object Photo for update
     */
    @Override
    public final void updatePhoto(final Photo photo) {
        photoDAO.updatePhoto(photo);
    }

    /**
     * Delete Photo by id.
     * 
     * @param id
     *            - Photo id
     * 
     */
    @Override
    public final void deletePhoto(final long id) {
        photoDAO.deletePhoto(id);
    }

    /**
     * @return last Photo id in DB
     */
    @Override
    public final long getLastId() {
        return photoDAO.getLastId();
    }

}
