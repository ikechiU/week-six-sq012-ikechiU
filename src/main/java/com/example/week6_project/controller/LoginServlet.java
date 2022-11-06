package com.example.week6_project.controller;

import com.example.week6_project.dao.LoginDao;
import com.example.week6_project.dao.impl.LoginDaoImpl;
import com.example.week6_project.dao.impl.UserDaoImpl;
import com.example.week6_project.dao.shared.Messages;
import com.example.week6_project.dao.shared.ProvideConnection;
import com.example.week6_project.model.UserData;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private LoginDao loginDao;

//    @Resource(name = "jdbc/facebook_db")
//    DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            loginDao = new LoginDaoImpl();
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
        HttpSession session = request.getSession();

        try {
            UserData userData = loginDao.getUserData(contact, password);
            if (userData != null) {
                System.out.println(userData.getPostList());
                String username = userData.getUser().getFirstname() + " " + userData.getUser().getLastname();
                session.setAttribute("USERNAME", username);
                session.setAttribute("USER", userData.getUser());
                session.setAttribute("ID", userData.getUser().getId());
                session.setAttribute("FIRSTNAME", userData.getUser().getFirstname());
                session.setAttribute("NAME", (userData.getUser().getFirstname() + " " + userData.getUser().getLastname()));
                session.setAttribute("LASTNAME", userData.getUser().getLastname());
                session.setAttribute("CONTACT", userData.getUser().getContact());
                session.setAttribute("PASSWORD", userData.getUser().getPassword());
                String day = day(userData.getUser().getDob());
                String month = month(userData.getUser().getDob());
                String year = year(userData.getUser().getDob());
                session.setAttribute("DAY", day);
                session.setAttribute("MONTH", month);
                session.setAttribute("YEAR", year);
                session.setAttribute("GENDER", userData.getUser().getGender());
                session.setAttribute("POSTS", userData.getPostList());
                response.sendRedirect("/week6_project_war_exploded/homepage.jsp");
            } else  {
                request.setAttribute("LoginResult", Messages.INCORRECT_LOGIN_DETAILS.getMessage());
                dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String day(String dob) {
        return dob.substring(0, 2);
    }

    private String month(String dob) {
        return dob.substring(2, 4);
    }

    private String year(String dob) {
        return dob.substring(4);
    }

}
