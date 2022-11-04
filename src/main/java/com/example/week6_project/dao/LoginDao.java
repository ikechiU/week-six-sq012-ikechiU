package com.example.week6_project.dao;

import com.example.week6_project.model.UserData;

public interface LoginDao {
    UserData getUserData(String contact, String password) throws Exception;
}
