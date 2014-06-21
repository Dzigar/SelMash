package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.Post;
import com.selfmash.model.User;

/**
 * 
 * @author Dzigar
 * 
 */
public interface PostDAO {

    /**
     * Save user action in DB.
     */
    void savePost(Post post);

    /**
     * Get posts of friends
     * 
     * @return list of friends posts
     */
    List<Post> getFollowPosts(long userId);

    /**
     * 
     * @param postId
     */
    void deletePost(Post post);

    /**
     * 
     * @param post
     * @param photo
     */
    void associatePhotoWithPost(Post post, User user);

    /**
     * 
     * @param post
     */
    void mergeWithEstimation(long postId, long estimationId);
}
