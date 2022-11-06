package com.example.week6_project.controller;

import com.example.week6_project.dao.CommentDao;
import com.example.week6_project.dao.PostDao;
import com.example.week6_project.dao.SignUpDao;
import com.example.week6_project.dao.impl.CommentDaoImpl;
import com.example.week6_project.dao.impl.CommentLikeDaoImpl;
import com.example.week6_project.dao.impl.PostDaoImpl;
import com.example.week6_project.dao.impl.SignUpDaoImpl;
import com.example.week6_project.dao.shared.Messages;
import com.example.week6_project.dao.shared.ProvideConnection;
import com.example.week6_project.model.Post;
import com.example.week6_project.model.User;
import com.example.week6_project.model.UserData;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    private SignUpDao signUpDao;
    private PostDao postDao;
    private CommentDao commentDao;
    private  RequestDispatcher dispatcher = null;

//    @Resource(name = "jdbc/facebook_db")
//    DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            signUpDao = new SignUpDaoImpl();
            postDao = new PostDaoImpl();
            commentDao = new CommentDaoImpl();
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
        boolean isExisting = signUpDao.isExisting(request.getParameter("contact"));
        if (!isExisting) {
            Optional<User> optionalUser = signUpDao.addUser(getUser(request));
            if (optionalUser.isPresent()) {
                System.out.println(optionalUser.get());
                request.setAttribute("CONTACT_LOGIN", optionalUser.get().getContact());
                request.setAttribute("CONTACT_PASSWORD", optionalUser.get().getPassword());
                request.setAttribute("RegistrationResult", Messages.SUCCESSFUL_REGISTRATION.getMessage());
                dispatcher = request.getRequestDispatcher("/login.jsp");
            }

        } else {
            request.setAttribute("RegistrationResult", Messages.UNSUCCESSFUL_REGISTRATION.getMessage());
            dispatcher = request.getRequestDispatcher("signup.jsp");
        }

        dispatcher.forward(request, response);
    }

    private User getUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String dob = year + "-" + month + "-" + day;
        String gender = request.getParameter("gender");

        return new User(firstName, lastName, contact, password, dob, gender);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            update(request, response);
        } catch (Exception e) {
           throw new ServletException(e);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        boolean isExisting = signUpDao.isExisting(request.getParameter("contact"));
        if (!isExisting) {
            User user = getUser(request);

            int user_id = Integer.parseInt(request.getParameter("id"));
            user.setId(user_id);
            UserData userData = signUpDao.updateUser(user);

            String name = userData.getUser().getFirstname() + " " + userData.getUser().getLastname();
            commentDao.updateUser((userData.getUser().getId()), name);
            postDao.updateUser(userData.getUser().getId(), name);

            List<Post> postList = postDao.getPostsByUserId(user_id);
            userData.setPostList(postList);

            System.out.println(userData.getPostList());
            String username = userData.getUser().getFirstname() + " " + userData.getUser().getLastname();
            session.setAttribute("USERNAME", username);
            session.setAttribute("POSTS", postList);

            response.sendRedirect("/week6_project_war_exploded/homepage.jsp");
        } else {
            request.setAttribute("RegistrationResult", Messages.UNSUCCESSFUL_UPDATE.getMessage());
            dispatcher = request.getRequestDispatcher("/update-user.jsp");
            dispatcher.forward(request, response);
        }

    }
}
