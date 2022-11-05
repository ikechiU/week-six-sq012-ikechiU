package com.example.week6_project.controller;

import com.example.week6_project.dao.CommentDao;
import com.example.week6_project.dao.CommentLikeDao;
import com.example.week6_project.dao.PostDao;
import com.example.week6_project.dao.impl.CommentDaoImpl;
import com.example.week6_project.dao.impl.CommentLikeDaoImpl;
import com.example.week6_project.dao.impl.PostDaoImpl;
import com.example.week6_project.dao.shared.ProvideConnection;
import com.example.week6_project.model.Post;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePageServlet", value = "/HomePageServlet")
public class HomePageServlet extends HttpServlet {

    private PostDao postDao;
    private CommentDao commentDao;
    private CommentLikeDao commentLikeDao;
    @Resource(name = "jdbc/facebook_db")
    DataSource dataSource;

    int user_id = 1;
    String firstname = "";
    String lastname = "";
    String contact = "";
    String name = "";
    String gender = "";
    String day = "";
    String month = "";
    String year = "";
    String password = "";

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        try{
//            postDao = new PostDaoImpl(dataSource);
//            commentDao = new CommentDaoImpl(dataSource);
//            commentLikeDao = new CommentLikeDaoImpl(dataSource);
//        }catch (Exception e) {
//            throw new ServletException(e);
//        }
//    }

    private void initImpl() {
        DataSource ds = ProvideConnection.dataSource();
        postDao = new PostDaoImpl(ds);
        commentDao = new CommentDaoImpl(ds);
        commentLikeDao = new CommentLikeDaoImpl(ds);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initImpl();
        setParameters(request);
        handleHttpRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initImpl();
        setParameters(request);
        handleHttpRequest(request, response);
    }

    private void handleHttpRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theCommand = request.getParameter("command");

        if (theCommand == null) {
            theCommand = "RELOAD";
        }

        switch (theCommand) {
            case "POST":
                try {
                    addPost(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "LIKE_POST":
                try {
                    likePost(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "DELETE":
                try {
                    deletePost(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "UPDATE_POST":
                try {
                    updatePost(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "COMMENT":
                try {
                    comment(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "UPDATE_COMMENT":
                try {
                    updateComment(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "DELETE_COMMENT":
                try {
                    deleteComment(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "LIKE_COMMENT":
                try {
                    likeComment(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "RELOAD":
            default:
                try {
                    reload(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    private void likePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        postDao.like(user_id, post_id);
        List<Post> postList = postDao.getPosts();
        loadSession(request, response, postList);
    }

    private void likeComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        int comment_id = Integer.parseInt(request.getParameter("comment_id"));
        commentLikeDao.like(comment_id, user_id, post_id);
        List<Post> postList = postDao.getPosts();
        loadSession(request, response, postList);
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        int comment_id = Integer.parseInt(request.getParameter("comment_id"));
        commentDao.deleteComment(comment_id, user_id, post_id);
        List<Post> postList = postDao.getPosts();
        loadSession(request, response, postList);
    }

    private void updateComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = request.getParameter("comment_update");
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        int comment_id = Integer.parseInt(request.getParameter("comment_id"));
        commentDao.updateComment(message, comment_id, user_id, post_id, name);
        List<Post> postList = postDao.getPosts();
        loadSession(request, response, postList);
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String message = request.getParameter("post_update");
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        postDao.update(user_id, post_id, message);
        List<Post> postList = postDao.getPosts();
        loadSession(request, response, postList);
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        postDao.delete(user_id, post_id);
        List<Post> postList = postDao.getPosts();
        loadSession(request, response, postList);
    }

    private void addPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String message = request.getParameter("new_post");
        List<Post> postList;

        if (message == null || message.isEmpty()) {
            postList = postDao.getPosts();
            session.setAttribute("BLANK_POST", "Post is empty.");
        } else {
           postList = postDao.addPost(message, user_id, name);
        }
        loadSession(request, response, postList);
    }

    private void setParameters(HttpServletRequest request) {
        user_id = Integer.parseInt(request.getParameter("user_id"));
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        contact = request.getParameter("contact");
        name = request.getParameter("name");
        gender = request.getParameter("gender");
        day = request.getParameter("day");
        month = request.getParameter("month");
        year = request.getParameter("year");
        password = request.getParameter("password");
    }

    private void loadSession(HttpServletRequest request, HttpServletResponse response, List<Post> postList) throws IOException {
        HttpSession session = request.getSession();

        session.setAttribute("NAME", name);
        session.setAttribute("ID", user_id);
        session.setAttribute("FIRSTNAME", firstname);
        session.setAttribute("LASTNAME", lastname);
        session.setAttribute("CONTACT", contact);
        session.setAttribute("DAY", day);
        session.setAttribute("MONTH", month);
        session.setAttribute("YEAR", year);
        session.setAttribute("GENDER", gender);
        session.setAttribute("PASSWORD", password);
        session.setAttribute("POSTS", postList);

        response.sendRedirect("/week6_project_war_exploded/homepage.jsp");
    }

    private void comment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        String comment = request.getParameter("new_comment");
        commentDao.addComment(comment, user_id, post_id, name);
        List<Post> postList = postDao.getPosts();
        loadSession(request, response, postList);
    }

    private void reload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Post> postList = postDao.getPosts();
        loadSession(request, response, postList);
    }
}
