package com.selfmash.service;

import java.util.List;

import com.selfmash.model.Photo;
import com.selfmash.model.User;

/**
 * @author Dzigar.
 */
public interface PhotoService {

    /**
     * 
     * @param photo
     *            - link to Photo object
     */
    void addphoto(Photo photo);

    /**
     * 
     * @param user
     *            - link to User object
     * @return list user photos
     */
    List<Photo> getUserPhotos(User user);

    /**
     * 
     * @param id
     *            - User id
     * @return object Photo
     */
    Photo getPhotoById(long id);

    /**
     * 
     * @param photo
     *            - object Photo for update
     */
    void updatePhoto(Photo photo);

    /**
     * 
     * @param user
     *            - object User
     * @return object Photo if user has account photo and return null if he
     *         don't have is
     */
    Photo getAccoutPhoto(User user);

    /**
     * 
     * @param id
     *            - id photo deleting
     */
    void deletePhoto(long id);

    /**
     * 
     * @return last id photos
     */
    long getLastId();
}
