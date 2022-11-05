package com.example.week6_project.dao.shared;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class ProvideConnection {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/facebook_db?allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "user", "user");
        } catch (Exception ex) {
            ex.printStackTrace();
            return  null;
        }
    }

//    public static DataSource dataSource() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setDatabaseName("facebook_db");
//        dataSource.setURL("jdbc:mysql://localhost:3306/facebook_db?allowPublicKeyRetrieval=true&serverTimezone=UTC");
//        dataSource.setUser("user");
//        dataSource.setPassword("user");
//        return dataSource;
//    }

//    public static DataSource dataSource(){
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setUser("user");
//        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
//        ds.setJdbcUrl("jdbc:mysql://localhost:3306/facebook_db?allowPublicKeyRetrieval=true&serverTimezone=UTC");
//        ds.setPassword("root");
//        return ds;
//    }


    public static DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("user");
        dataSource.setPassword("user");
        dataSource.setUrl("jdbc:mysql://localhost:3306/facebook_db?allowPublicKeyRetrieval=true&serverTimezone=UTC");
        dataSource.setMaxActive(20);
        dataSource.setMaxIdle(5);
        dataSource.setMaxWait(10000);
        return dataSource;
    }
}
