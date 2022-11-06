package com.example.week6_project.dao;

import com.example.week6_project.model.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDao {
    List<Post> getPosts() throws Exception;
    List<Post> getPostsByUserId(int user_id) throws Exception;
    String updateUser(int id, String name) throws Exception;
    List<Post> addPost(String message, int user_id, String name) throws Exception;
    List<Post> delete(int user_id, int post_id) throws Exception;
    List<Post> update(int user_id, int post_id, String message) throws Exception;
    List<Post> like(int user_id, int post_id) throws Exception;
}
