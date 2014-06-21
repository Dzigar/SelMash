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
import com.selfmash.strings.PostQueries;

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
            associatePhotoWithPost(post, post.getUser());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Post> getFollowPosts(long userId) {
        try {
            return getCurrentSession()
                    .createSQLQuery(PostQueries.GET_FOLLOW_POSTS)
                    .addEntity(Post.class).setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return new ArrayList<Post>();
    }

    @Override
    public void deletePost(Post post) {
        try {
            getCurrentSession().createQuery(PostQueries.DELETE_POST_BY_ID)
                    .setParameter("postId", post.getId()).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void associatePhotoWithPost(Post post, User user) {
        try {
            getCurrentSession()
                    .createSQLQuery(PostQueries.INSERT_INTO_POST_USER)
                    .setParameter("userId", user.getId())
                    .setParameter("postId", post.getId()).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void mergeWithEstimation(long postId, long estimationId) {
        try {
            getCurrentSession()
                    .createSQLQuery(PostQueries.MERGE_WITH_ESTIMATION)
                    .setParameter("estimationId", estimationId)
                    .setParameter("postId", postId).executeUpdate();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

}
