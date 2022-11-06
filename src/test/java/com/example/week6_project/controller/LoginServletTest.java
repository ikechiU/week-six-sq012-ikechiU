package com.example.week6_project.controller;

import com.example.week6_project.dao.LoginDao;
import com.example.week6_project.dao.mock_impl.LoginDaoMockImpl;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest extends Mockito {
//
//    @Mock
//    HttpSession mockHttpSession;
//
//    MockHttpServletRequest request = new MockHttpServletRequest();
//    MockHttpServletResponse response = new MockHttpServletResponse();
//    MockHttpSession session = new MockHttpSession();
//
//    @Mock
//    LoginDao loginDao;
//
////    @BeforeEach
////    void init() throws NamingException {
////        dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/facebook_db");
////    }
//
//    @Test
//    void testLoginServlet() throws Exception {
////        when(loginDao.getUserData("ikechi@gmail.com", "123456")).thenReturn(new LoginDaoMockImpl().getUserData("ikechi@gmail.com", "123456"));
//
//        LoginServlet loginServlet = new LoginServlet(new LoginDaoMockImpl());
//        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
//        HttpSession mockedSession = Mockito.mock(HttpSession.class);
//        ServletContext mockedServletContext = Mockito.mock(ServletContext.class);
//
//        //When
//        Mockito.doReturn(mockedServletContext).when(mockedRequest).getServletContext();
//        Mockito.when(mockedRequest.getParameter("contact")).thenReturn("ikechi@gmail.com");
//        Mockito.when(mockedRequest.getParameter("password")).thenReturn("123456");
//        Mockito.when(mockedSession.getAttribute("ikechi@gmail.com")).thenReturn("Ikechi Ucheagwu");
//        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
//
//        loginServlet.doPost(mockedRequest, mockedResponse);
//
//        verify(mockedRequest, atLeast(1)).getParameter("contact");
//        assertEquals("Ikechi Ucheagwu", mockedSession.getAttribute("ikechi@gmail.com"));
//
//    }
//
//    @Test
//    void doPost() {
//    }
}