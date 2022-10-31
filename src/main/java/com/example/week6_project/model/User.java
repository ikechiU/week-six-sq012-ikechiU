package com.example.week6_project.model;

import java.util.List;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String contact;
    private String password;
    private String dob;
    private String gender;
    private List<Post> posts;

    public User() {
    }

    public User(String firstname, String lastname, String contact, String password, String dob, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    public User(int id, String firstname, String lastname, String contact, String password, String dob, String gender) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    public User(int id, String firstname, String lastname, String contact, String password, String dob, String gender, List<Post> posts) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", contact='" + contact + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", posts=" + posts +
                '}';
    }
}
