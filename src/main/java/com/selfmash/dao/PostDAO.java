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
    List<Post> getFriendsPosts(List<User> friends);

}
