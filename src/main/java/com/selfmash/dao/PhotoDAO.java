package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.Photo;
import com.selfmash.model.User;

/**
 * 
 * @author Dzigar.
 * 
 */
public interface PhotoDAO {

    /**
     * Save Photo in DB.
     * 
     * @param photo
     *            - object Photo class.
     */
    void addphoto(Photo photo);

    /**
     * Get list of photo from DB by User object.
     * 
     * @param user
     *            - object User class
     * @return list of Photo
     */
    List<Photo> getUserPhotos(User user);

    /**
     * Get photo from DB by photo id.
     * 
     * @param id
     *            - Photo id.
     * @return object Photo class
     */
    Photo getPhotoById(long id);

    /**
     * Update changed photo.
     * 
     * @param photo
     *            - object Photo for update
     */
    void updatePhoto(Photo photo);

    /**
     * Delete photo by id.
     * 
     * @param id
     *            - Photo id
     */
    void deletePhoto(long id);

}
