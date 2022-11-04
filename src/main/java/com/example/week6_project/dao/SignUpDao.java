package com.example.week6_project.dao;

import com.example.week6_project.model.User;
import com.example.week6_project.model.UserData;

import java.sql.SQLException;

public interface SignUpDao {
    void addUser(User user) throws Exception;
    boolean isExisting(String contact) throws Exception;
    UserData updateUser(User user) throws Exception;
}
