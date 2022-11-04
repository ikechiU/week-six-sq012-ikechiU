package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.SignUpDao;
import com.example.week6_project.model.User;
import com.example.week6_project.model.UserData;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.example.week6_project.dao.shared.DbUtils.close;
import static com.example.week6_project.dao.shared.DbUtils.encryptPassword;

public class SignUpDaoImpl implements SignUpDao {
    private final DataSource dataSource;

    public SignUpDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addUser(User user) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "insert into users "
                    + "(firstname, lastname, contact, password, dob, gender) "
                    + "values (?, ?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, user.getFirstname());
            myStmt.setString(2, user.getLastname());
            myStmt.setString(3, user.getContact());
            myStmt.setString(4, encryptPassword(user.getPassword()));
            myStmt.setString(5, user.getDob());
            myStmt.setString(6, user.getGender());

            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }
    }

    @Override
    public boolean isExisting(String contact) throws Exception {
        List<User> users = new UserDaoImpl(dataSource).getUsers();
        for (User user: users) {
            if (user.getContact().equals(contact)) return true;
        }
        return false;
    }

    @Override
    public UserData updateUser(User user) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        UserData userData = new UserData();

        try {
            myConn = dataSource.getConnection();

            String sql = "update users set firstname=?, lastname=?, contact=?, password=?, dob=?, gender=? where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, user.getFirstname());
            myStmt.setString(2, user.getLastname());
            myStmt.setString(3, user.getContact());
            myStmt.setString(4, encryptPassword(user.getPassword()));
            myStmt.setString(5, user.getDob());
            myStmt.setString(6, user.getGender());
            myStmt.setInt(7, user.getId());

            myStmt.execute();

            userData.setUser(user);

        } finally {
            close(myConn, myStmt, null);
        }
        return userData;
    }
}
