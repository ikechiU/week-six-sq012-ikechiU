package com.example.week6_project.dao.shared;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

public class DbUtils {
    public static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if(myStmt != null) {
                myStmt.close();
            }

            if(myConn != null) {
                myConn.close(); //it doesn't really close, it only goes back to connection pool to be available for another user
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptPassword(String password){
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String decryptPassword(String encryptedString){
        return new String (Base64.getMimeDecoder().decode(encryptedString));
    }
}
