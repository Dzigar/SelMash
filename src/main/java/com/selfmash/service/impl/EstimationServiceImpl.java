package com.selfmash.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.beans.PostBean;
import com.selfmash.dao.EstimationDAO;
import com.selfmash.model.Estimation;
import com.selfmash.model.User;
import com.selfmash.service.EstimationService;
import com.selfmash.service.PhotoService;

@Service("estimationServiceImpl")
@Transactional
public class EstimationServiceImpl implements EstimationService {

    @Autowired
    private EstimationDAO estimationDAO;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PostBean postBean;

    @Override
    public void addEstimation(Estimation estimation) {
        if (!estimationDAO.isAppreciated(estimation.getUser().getId(),
                estimation.getPhoto().getId())) {
            postBean.addPost(estimation); // Create post with estimation photo.
        }
    }

    @Override
    public void removeEstimationsByPhotoId(long id) {
        estimationDAO.removeEstimationsByPhotoId(id);
    }

    @Override
    public List<Estimation> getEstimationsByPhotoId(long id) {
        return estimationDAO.getEstimationsByPhotoId(id);
    }

    @Override
    public List<User> getAdmirersByPhotoId(long photoId) {
        return estimationDAO.getAdmirersByPhotoId(photoId);
    }

}
