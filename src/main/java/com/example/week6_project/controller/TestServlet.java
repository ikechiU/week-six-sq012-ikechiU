package com.example.week6_project.controller;


import com.example.week6_project.dbutil.impl.UserDbUtilImpl;
import com.example.week6_project.model.CommentLike;
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
import java.util.List;


@WebServlet(name = "TestServlet", value = "/controller/TestServlet")
public class TestServlet extends HttpServlet {

    private UserDbUtilImpl userDbUtil;

    @Resource(name = "jdbc/facebook_db")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try{
            userDbUtil = new UserDbUtilImpl(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/plain");

            List<User> usersList = userDbUtil.getUsers();
            for (User user: usersList) {
                out.println(user);
            }

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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
