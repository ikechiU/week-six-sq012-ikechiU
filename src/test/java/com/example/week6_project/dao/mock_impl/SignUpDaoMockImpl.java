package com.example.week6_project.dao.mock_impl;

import com.example.week6_project.dao.SignUpDao;
import com.example.week6_project.model.User;
import com.example.week6_project.model.UserData;

import java.util.List;
import java.util.Optional;

import static com.example.week6_project.dao.shared.DbUtils.encryptPassword;

public class SignUpDaoMockImpl implements SignUpDao {

    @Override
    public Optional<User> addUser(User user) throws Exception {
        List<User> userList = new UserDaoMockImpl().getUsers();
        if (!isExisting(user.getContact())) {
            user.setId((int) System.currentTimeMillis());
            userList.add(user);
            System.out.println("DB size is: " + userList.size());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public boolean isExisting(String contact) throws Exception {
        List<User> userList = new UserDaoMockImpl().getUsers();
        for (User user : userList) {
            if (user.getContact().equals(contact)) return true;
        }
        return false;
    }

    @Override
    public UserData updateUser(User user) throws Exception {
        List<User> userList = new UserDaoMockImpl().getUsers();
        for (User u : userList) {
            if (u.getId() == user.getId()) {
                u.setFirstname(user.getFirstname());
                u.setLastname(user.getLastname());
                u.setContact(user.getContact());
                u.setDob(user.getDob());
                u.setPassword(encryptPassword(user.getPassword()));
                u.setGender(user.getGender());
                System.out.println(u);
                UserData userData = new UserData();
                userData.setUser(u);
                return userData;
            }
        }
        System.out.println("No update done");
        return null;
    }
}
