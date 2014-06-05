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
    void addPhoto(Photo photo);

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
     * @param id
     *            - id photo deleting
     */
    void deletePhoto(Photo photo);
}
