package com.example.week6_project.dao;

import com.example.week6_project.model.CommentLike;

import java.util.List;

public interface CommentLikeDao {
    List<CommentLike> getCommentLikesByCommentId(int post_id) throws Exception;
    List<CommentLike> getCommentLikes() throws Exception;
    void like(int comment_id, int user_id, int post_id) throws Exception;
}
