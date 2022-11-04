package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.LoginDao;
import com.example.week6_project.model.Post;
import com.example.week6_project.model.User;
import com.example.week6_project.model.UserData;

import javax.sql.DataSource;
import java.util.List;

import static com.example.week6_project.dao.shared.DbUtils.decryptPassword;

public class LoginDaoImpl implements LoginDao {

    private final DataSource dataSource;

    public LoginDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserData getUserData(String contact, String password) throws Exception {
        List<User> users = new UserDaoImpl(dataSource).getUsers();
        List<Post> posts = new PostDaoImpl(dataSource).getPosts();
        for (User user: users) {
            if (user.getContact().equals(contact) && decryptPassword(user.getPassword()).equals(password)) {
                return new UserData(user, posts);
            }
        }
        return null;
    }
}
