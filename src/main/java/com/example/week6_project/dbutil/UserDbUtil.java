package com.example.week6_project.dbutil;

import com.example.week6_project.model.*;

import java.util.List;

public interface UserDbUtil {
    List<User> getUsers() throws Exception;
    List<Post> getPosts() throws Exception;
    List<PostLike> getPostLikes() throws Exception;
    List<Comment> getComments() throws Exception;
    List<CommentLike> getCommentLikes() throws Exception;
    void addUser(User user) throws Exception;
    boolean isExisting(String contact) throws Exception;
    User getUser(String contact, String password) throws Exception;
}
