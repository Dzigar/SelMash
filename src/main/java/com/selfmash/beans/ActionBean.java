package com.selfmash.beans;

import org.springframework.beans.factory.annotation.Autowired;

import com.selfmash.beans.enums.ActionBody;
import com.selfmash.model.Post;
import com.selfmash.model.User;
import com.selfmash.service.PhotoService;
import com.selfmash.service.PostService;
import com.selfmash.service.UserService;

public class ActionBean {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    public void addFriendAction(long userId, long friendId) {
        User user = userService.getUserById(userId);
        User friend = userService.getUserById(friendId);
        postService.savePost(new Post(user, friend, null, ActionBody.ADD_USER));
    }
}
