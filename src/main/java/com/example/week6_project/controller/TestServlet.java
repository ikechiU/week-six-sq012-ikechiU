package com.example.week6_project.controller;


import com.example.week6_project.dao.UserDao;
import com.example.week6_project.dao.impl.UserDaoImpl;
import com.example.week6_project.dao.shared.ProvideConnection;
import com.example.week6_project.model.User;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "TestServlet", value = "/controller/TestServlet")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            PrintWriter out = response.getWriter();
            UserDao userDao = new UserDaoImpl();
//            response.setContentType("text/plain");
//            List<User> usersList = userDao.getUsersDriverManager();
//            for (User user: usersList) {
//                out.println(user.getFirstname());
//            }

//            for (User user: usersList) {
//                out.println(user.getPosts());
//            }
//            List<CommentLike> commentLikes = userDbUtil.getCommentLikesByPostId(11);
//            for(CommentLike commentLike : commentLikes) {
//                out.println(commentLike);
//            }

//            List<Comment> comments = userDbUtil.getComments();
//            for(Comment comment : comments) {
//                out.println(comment);
//            }
            String name = userDao.getUsers().get(0).getFirstname();
            out.println(name);
            out.println(name);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        out.println(contact);
        out.println(password);
    }



}
