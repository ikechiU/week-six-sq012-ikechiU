package com.example.week6_project.controller;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

import java.beans.PropertyVetoException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest extends Mockito {

    @Mock
    HttpSession mockHttpSession;

//    @BeforeEach
//    void init() throws NamingException {
//        dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/facebook_db");
//    }

    @Test
    void testLoginServlet() throws Exception {
        LoginServlet loginServlet = new LoginServlet();
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession mockedSession = Mockito.mock(HttpSession.class);
//        ServletContext mockedServletContext = Mockito.mock(ServletContext.class);
        PrintWriter mockedOut = Mockito.mock(PrintWriter.class);
//        patient = Mockito.mock(PatientBean.class);

//        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
        Mockito.when(mockedRequest.getParameter("contact")).thenReturn("ikechi@gmail.com");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("123456");
        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
//        Mockito.when(pm.ReturnPatientByKey("fiscalCode", "password"));
//        Mockito.when(mockedResponse.getWriter()).thenReturn(mockedOut);

//When
        loginServlet.doPost(mockedRequest, mockedResponse);

//Then
//        Mockito.verify(mockedOut).println("1");
//        Mockito.verify(mockedSession).getAttribute("USERNAME");
        assertEquals(mockedRequest.getSession().getAttribute("USERNAME"), "Ikechi Ucheagwu");

//        Mockito.verify(mockedSession).setAttribute(Mockito.eq("user"), patient);
//        Mockito.verify(mockUserService, Mockito.times(1)).getByUsername("luboslav");
    }

    @Test
    void doPost() {
    }
}