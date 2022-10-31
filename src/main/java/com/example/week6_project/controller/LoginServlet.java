package com.example.week6_project.controller;

import com.example.week6_project.dbutil.impl.UserDbUtilImpl;
import com.example.week6_project.dbutil.shared.Messages;
import com.example.week6_project.model.User;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserDbUtilImpl userDbUtil;

    @Resource(name = "jdbc/facebook_db")
    DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            userDbUtil = new UserDbUtilImpl(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");

        RequestDispatcher dispatcher = null;

        try {
            User user = userDbUtil.getUser(contact, password);
            if (user != null) {
                request.setAttribute("LoginResult", user);
                dispatcher = request.getRequestDispatcher("/homepage.html");
            } else  {
                request.setAttribute("LoginResult", Messages.INCORRECT_LOGIN_DETAILS.getMessage());
                dispatcher = request.getRequestDispatcher("/login.jsp");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
