package com.example.week6_project.dao.mock_impl;

import com.example.week6_project.dao.UserDao;
import com.example.week6_project.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMockImpl implements UserDao {
    @Override
    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User(1, "Ikechi", "Ucheagwu", "08067994494", "MTIzNDU2", "12-10-1985","Male");
        User user2 = new User(2, "Saint", "Michael", "ikechi@gmail.com", "MTIzNDU2", "12-10-1986","Male");
        User user3 = new User(3, "Anne", "Smith", "anne@gmail.com", "MTIzNDU1", "12-10-1987","Female");
        users.add(user);
        users.add(user2);
        users.add(user3);
        return users;
    }

    @Override
    public User getUserNameById(int user_id) throws Exception {
        for (User user : getUsers()) {
            if (user_id == user.getId()) {
                return user;
            }
        }
        return null;
    }

}
