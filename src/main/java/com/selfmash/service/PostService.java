package com.selfmash.service;

import java.util.List;

import com.selfmash.model.Post;

public interface PostService {

    void savePost(Post post);

    List<Post> getFollowPosts(long userId);

    void deletePost(Post post);

    void mergeWithEstimation(long postId, long estimationId);
}
