package com.example.week6_project.dao;

import com.example.week6_project.model.*;

import java.util.List;

public interface UserDao {
    List<User> getUsers() throws Exception;
    User getUserNameById(int user_id) throws Exception;
}
