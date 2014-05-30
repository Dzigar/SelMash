package com.selfmash.beans;

import org.springframework.beans.factory.annotation.Autowired;

import com.selfmash.beans.enums.ActionBody;
import com.selfmash.model.Photo;
import com.selfmash.model.Post;
import com.selfmash.service.PhotoService;
import com.selfmash.service.PostService;
import com.selfmash.service.UserService;

/**
 * In this bean are created and stored through PostService object of Post
 * entity.
 * 
 * @author Dzigar
 * 
 */
public class PostBean {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    public void addPost(long userId1, long userId2, Photo photo, ActionBody body) {
        postService.savePost(new Post(userService.getUserById(userId1),
                userService.getUserById(userId2), photo, ActionBody.SUBSCRIBE));
    }
}
