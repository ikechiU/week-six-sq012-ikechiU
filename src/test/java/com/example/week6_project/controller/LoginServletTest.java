package com.example.week6_project.controller;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

import java.beans.PropertyVetoException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest extends Mockito {

    DataSource dataSource;

    @BeforeEach
    void init() throws NamingException {
////        ComboPooledDataSource basicDataSource = new ComboPooledDataSource();
//       BasicDataSource basicDataSource = new BasicDataSource();
//       basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//       basicDataSource.setUrl("jdbc:mysql://localhost:3306/facebook_db");
//       basicDataSource.setUsername("user");
//       basicDataSource.setPassword("user");
//       dataSource = basicDataSource;

       dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/facebook_db");
    }

    @Test
    void testLoginServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("contact")).thenReturn("Ikechi");
        when(request.getParameter("password")).thenReturn("123456");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

//        LoginServlet loginServlet = new LoginServlet(dataSource);
//        loginServlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("username");
        writer.flush();
        assertTrue(stringWriter.toString().contains("Ikechi"));
    }

    @Test
    void doPost() {
    }
}