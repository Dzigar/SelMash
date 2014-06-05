package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.Estimation;
import com.selfmash.model.User;

public interface EstimationDAO {

    /**
     * 
     * @param estimation
     */
    void addEstimation(Estimation estimation);

    /**
     * 
     * @param id
     * @return
     */
    List<Estimation> getEstimationsByPhotoId(long id);

    /**
     * 
     * @param id
     */
    void removeEstimationsByPhotoId(long id);

    /**
     * 
     * @param photoId
     */
    List<User> getAdmirersByPhotoId(long photoId);

    /**
     * 
     * @param userId
     * @param photoId
     * @return
     */
    boolean isAppreciated(long userId, long photoId);
}
