package com.example.week6_project.dao.mock_impl;

import com.example.week6_project.dao.LoginDao;
import com.example.week6_project.dao.mock_impl.PostDaoMockImpl;
import com.example.week6_project.dao.mock_impl.UserDaoMockImpl;
import com.example.week6_project.model.*;

import java.util.List;

import static com.example.week6_project.dao.shared.DbUtils.decryptPassword;

public class LoginDaoMockImpl implements LoginDao {

    @Override
    public UserData getUserData(String contact, String password) throws Exception {
        List<User> users = new UserDaoMockImpl().getUsers();
        List<Post> posts = new PostDaoMockImpl().getPosts();
        for (User user: users) {
            if (user.getContact().equals(contact) && decryptPassword(user.getPassword()).equals(password)) {
                return new UserData(user, posts);
            }
        }
        return null;
    }

}
