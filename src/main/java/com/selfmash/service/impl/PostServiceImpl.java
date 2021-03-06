package com.selfmash.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.PostDAO;
import com.selfmash.model.Post;
import com.selfmash.service.PostService;
import com.selfmash.service.UserService;

@Service("actionService")
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void savePost(Post post) {
        try {
            postDAO.savePost(post);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public List<Post> getFollowPosts(long userId) {
        return postDAO.getFollowPosts(userId);
    }

    @Override
    public void deletePost(Post post) {
        postDAO.deletePost(post);
    }

    @Override
    public void mergeWithEstimation(long postId, long estimationId) {
        postDAO.mergeWithEstimation(postId, estimationId);
    }

}
