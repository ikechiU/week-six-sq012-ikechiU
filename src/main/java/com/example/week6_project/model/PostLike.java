package com.example.week6_project.model;

public class PostLike {
    private int id;
    private boolean like; //mapped to postId
    private int post_id;
    private int user_id;

    public PostLike(boolean like, int post_id, int user_id) {
        this.like = like;
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public PostLike(int id, boolean like, int post_id, int user_id) {
        this.id = id;
        this.like = like;
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
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

    @Override
    public String toString() {
        return "PostLike{" +
                "id=" + id +
                ", like=" + like +
                ", post_id=" + post_id +
                ", user_id=" + user_id +
                '}';
    }
}
