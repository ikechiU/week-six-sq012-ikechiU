
package com.example.week6_project.dao.impl;


import com.example.week6_project.dao.UserDao;
import com.example.week6_project.dao.shared.DbUtils;
import com.example.week6_project.dao.shared.ProvideConnection;
import com.example.week6_project.model.*;

import javax.sql.DataSource;
import java.security.Provider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DbUtils implements UserDao {
    private Connection connection;

    private final DataSource dataSource = ProvideConnection.dataSource();

    @Override
    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "SELECT * FROM users";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String firstname = myRs.getString("firstname");
                String lastname = myRs.getString("lastname");
                String contact = myRs.getString("contact");
                String password = myRs.getString("password");
                String dob = myRs.getString("dob");
                String gender = myRs.getString("gender");

                User user = new User(id, firstname, lastname, contact, password, dob, gender);
                users.add(user);
            }

            //return userList(users);
            return users;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }


    @Override
    public User getUserNameById(int user_id) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "SELECT * FROM users WHERE id=" + user_id;
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            if (myRs.next()) {
                String firstname = myRs.getString("firstname");
                String lastname = myRs.getString("lastname");
                return new User(firstname, lastname);
            } else {
                return new User();
            }

        } finally {
            close(myConn, myStmt, myRs);
        }
    }

}
