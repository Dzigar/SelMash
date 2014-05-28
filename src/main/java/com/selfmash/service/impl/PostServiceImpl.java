package com.selfmash.service.impl;

import java.util.List;

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

    @Override
    public void savePost(Post post) {
        postDAO.savePost(post);
    }

    @Override
    public List<Post> getFriendsPosts(long userId) {
        return postDAO.getFriendsPosts(userService.getFriendsList(userId));
    }

}
