package com.selfmash.beans;

import org.springframework.beans.factory.annotation.Autowired;

import com.selfmash.model.Estimation;
import com.selfmash.model.Photo;
import com.selfmash.model.Post;
import com.selfmash.model.User;
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

    /**
     * Called when user subscribes another user.
     * 
     * @param userId
     *            - id user admirer
     * @param userId2
     *            - id user follower
     */
    public void addPost(long userId, long userId2) {
        postService.savePost(new Post(userService.getUserById(userId),
                userService.getUserById(userId2)));
    }

    /**
     * Called when user upload photo.
     * 
     * @param userId
     *            - user who uploaded photo.
     * @param photo
     *            - some user photo.
     */
    public void addPost(User user, Photo photo) {
        postService.savePost(new Post(user, photo));
    }

    /**
     * Called when user appreciate photo.
     * 
     * @param estimation
     */
    public void addPost(Post post) {
        postService.savePost(post);
    }

    public void addPost(Estimation estimation) {
        postService.savePost(new Post(estimation.getUser(), estimation));
    }
}
