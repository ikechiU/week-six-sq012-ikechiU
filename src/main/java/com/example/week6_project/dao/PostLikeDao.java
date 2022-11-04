package com.example.week6_project.dao;

import com.example.week6_project.model.PostLike;

import java.util.List;

public interface PostLikeDao {
    List<PostLike> getPostLikes() throws Exception;
    List<PostLike> getPostLikesByPostId(int post_id) throws Exception;
}
