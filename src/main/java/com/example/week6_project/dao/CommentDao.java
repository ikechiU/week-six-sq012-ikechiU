package com.example.week6_project.dao;

import com.example.week6_project.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    List<Comment> getComments() throws Exception;
    List<Comment> getCommentsByPostId(int post_id) throws Exception;
    void updateUser(int id, String name) throws SQLException;
    void addComment(String comment, int user_id, int post_id, String name) throws Exception;
    void updateComment(String comment, int comment_id, int user_id, int post_id, String name) throws Exception;
    void deleteComment(int comment_id, int user_id, int post_id) throws Exception;
}
