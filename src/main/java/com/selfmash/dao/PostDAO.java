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
    List<Post> getFollowPosts(List<User> friends);

    /**
     * 
     * @param postId
     */
    void deletePost(Post post);

    /**
     * 
     * @param userId
     */
    void removeUser(long postId, long userId);

    /**
     * 
     * @param followerId
     */
    void removeFollower(long postId, long followerId);
}
