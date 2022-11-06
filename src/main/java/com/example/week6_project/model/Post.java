package com.example.week6_project.model;

import java.util.List;

public class Post {
    private int id; //mapped to userId
    private String message;
    private List<PostLike> postLikes;
    private List<Comment> comments;
    private int user_id;
    private String name;

    public Post() {

    }

    public Post(String message, int user_id, String name) {
        this.message = message;
        this.user_id = user_id;
        this.name = name;
    }

    public Post(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public Post(int id, String message, int user_id) {
        this.id = id;
        this.message = message;
        this.user_id = user_id;
    }

    public Post(int id, String message, int user_id, String name) {
        this.id = id;
        this.message = message;
        this.user_id = user_id;
        this.name = name;
    }

    public Post(int id, String message, List<PostLike> postLikes, List<Comment> comments, int user_id) {
        this.id = id;
        this.message = message;
        this.postLikes = postLikes;
        this.comments = comments;
        this.user_id = user_id;
    }

    public Post(int id, String message, List<PostLike> postLikes, List<Comment> comments, int user_id, String name) {
        this.id = id;
        this.message = message;
        this.postLikes = postLikes;
        this.comments = comments;
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

    public List<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", postLikes=" + postLikes +
                ", comments=" + comments +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }
}
