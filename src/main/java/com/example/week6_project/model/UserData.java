package com.example.week6_project.model;

import java.util.List;

public class UserData {
    User user;
    List<Post> postList;

    public UserData() {

    }

    public UserData(User user, List<Post> postList) {
        this.user = user;
        this.postList = postList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "user=" + user +
                ", postList=" + postList +
                '}';
    }
}
