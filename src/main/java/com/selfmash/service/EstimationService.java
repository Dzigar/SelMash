package com.selfmash.service;

import java.util.List;

import com.selfmash.model.Estimation;
import com.selfmash.model.User;

public interface EstimationService {

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
     * @return
     */
    List<User> getAdmirersByPhotoId(long photoId);

    /**
     * 
     * @param estimation
     */
    void deleteEstimation(Estimation estimation);
    
    /**
     * 
     * @param userId
     * @param photoId
     * @return
     */
    boolean isAppreciate(long userId, long photoId);
}
