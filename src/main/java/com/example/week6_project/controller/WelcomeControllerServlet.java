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

@WebServlet(name = "WelcomeControllerServlet", value = "/WelcomeControllerServlet")

public class WelcomeControllerServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            //read the command parameter
            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "REGISTER";
            }

            switch (theCommand) {
                case "LOGIN":
                    login(request, response);
                    break;
                case "REGISTER":
                default:
                    addUser(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String dob = year + "-" + month + "-" + day;
        String gender = request.getParameter("gender");

        User user = new User(firstName, lastName, contact, password, dob, gender);
        RequestDispatcher dispatcher = null;

        boolean isExisting = userDbUtil.isExisting(contact);
        if (!isExisting) {
            userDbUtil.addUser(user);
            request.setAttribute("RegistrationResult", Messages.SUCCESSFUL_REGISTRATION.getMessage());
            dispatcher = request.getRequestDispatcher("/login.jsp");
        } else {
            request.setAttribute("RegistrationResult", Messages.UNSUCCESSFUL_REGISTRATION.getMessage());
            dispatcher = request.getRequestDispatcher("/index.jsp");
        }

        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
