package com.selfmash.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.selfmash.dao.PostDAO;
import com.selfmash.model.Post;
import com.selfmash.model.User;
import com.selfmash.service.UserService;

@Repository
public class PostDAOImpl implements PostDAO {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(getClass());

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void savePost(Post post) {
        try {
            getCurrentSession().save(post);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public List<Post> getFollowPosts(List<User> following) {
        List<Post> posts = new ArrayList<Post>();
        for (User user : following) {
            posts.addAll(user.getUserPosts());
        }
        return posts;
    }

}
