package com.example.week6_project.model;

public class CommentLike {
    private long id; //mapped to commentId;
    private boolean like;
    private int comment_id;
    private int user_id;
    private int post_id;

    public CommentLike(boolean like, int comment_id, int user_id, int post_id) {
        this.like = like;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public CommentLike(long id, boolean like, int comment_id, int user_id, int post_id) {
        this.id = id;
        this.like = like;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getPost_id() {
        return post_id;
    }

    @Override
    public String toString() {
        return "CommentLike{" +
                "id=" + id +
                ", like=" + like +
                ", comment_id=" + comment_id +
                ", user_id=" + user_id +
                ", post_id=" + post_id +
                '}';
    }
}
