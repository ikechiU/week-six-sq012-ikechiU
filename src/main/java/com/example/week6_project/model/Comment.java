package com.example.week6_project.model;

import java.util.List;

public class Comment {
    private int id; //mapped to postId
    private String message;
    private List<CommentLike> commentLikes;
    private int post_id;
    private int user_id;
    private String name;

    public Comment() {
    }

    public Comment(String message, int post_id, int user_id, String name) {
        this.message = message;
        this.post_id = post_id;
        this.user_id = user_id;
        this.name = name;
    }

    public Comment(int id, String message, int post_id, int user_id, String name) {
        this.id = id;
        this.message = message;
        this.post_id = post_id;
        this.user_id = user_id;
        this.name = name;
    }

    public Comment(int id, String message, List<CommentLike> commentLikes, int post_id, int user_id, String name) {
        this.id = id;
        this.message = message;
        this.commentLikes = commentLikes;
        this.post_id = post_id;
        this.user_id = user_id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CommentLike> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(List<CommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", commentLikes=" + commentLikes +
                ", post_id=" + post_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }
}
