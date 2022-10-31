package com.example.week6_project.dbutil.impl;


import com.example.week6_project.dbutil.UserDbUtil;
import com.example.week6_project.dbutil.shared.Messages;
import com.example.week6_project.model.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDbUtilImpl implements UserDbUtil {
    private final DataSource dataSource;

    public UserDbUtilImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getUsers() throws Exception{
        List<User> users = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "SELECT * FROM users";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String firstname = myRs.getString("firstname");
                String lastname = myRs.getString("lastname");
                String contact = myRs.getString("contact");
                String password = myRs.getString("password");
                String dob = myRs.getString("dob");
                String gender = myRs.getString("gender");

                User user = new User(id, firstname, lastname, contact, password, dob, gender);
                users.add(user);
            }

            for(User user : users) {
                int id = user.getId();
                List<Post> posts = getPostsByUserId(id);
                user.setPosts(posts);
            }

            return users;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    public List<Post> getPostsByUserId(int user_id) throws Exception {
        List<Post> posts = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from post where user_id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, user_id);
            myRs = myStmt.executeQuery();

            while (true) {
                if (myRs.next()) {
                    int id = myRs.getInt("id");
                    List<PostLike> postLikes = getPostLikesByPostId(id);
                    String message = myRs.getString("message");
                    Post post = new Post(id, message, user_id, postLikes);
                    posts.add(post);
                } else break;
            }

            for(Post post: posts) {
                int id = post.getId();
                List<Comment> comments = getCommentsByPostId(id);
                post.setComments(comments);
            }

            return posts;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    public List<PostLike> getPostLikesByPostId(int post_id) throws Exception {
        List<PostLike> postLikes = new ArrayList<>();

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from post_like where post_id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, post_id);
            myRs = myStmt.executeQuery();

            while (true) {
                if (myRs.next()) {
                    int id = myRs.getInt("id");
                    boolean like = myRs.getBoolean("like");
                    int user_id = myRs.getInt("user_id");
                    int nPost_id = myRs.getInt("post_id");
                    PostLike postLike = new PostLike(id, like, nPost_id, user_id);
                    postLikes.add(postLike);
                } else break;
            }

            return postLikes;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    public List<Comment> getCommentsByPostId(int post_id) throws Exception{
        List<Comment> comments = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from comment where post_id=" + post_id;
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (true) {
                if (myRs.next()) {
                    int id = myRs.getInt("id");
                    String message = myRs.getString("message");
                    int user_id = myRs.getInt("user_id");
                    Comment comment = new Comment(id, message, post_id, user_id);
                    comments.add(comment);
                }else break;
            }

            for (Comment comment : comments) {
                int id = comment.getPost_id();
                List<CommentLike> commentLikes = getCommentLikesByPostId(id);
                comment.setCommentLikes(commentLikes);
            }
            return comments;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    public List<CommentLike> getCommentLikesByPostId(int post_id) throws Exception{
        List<CommentLike> commentLikes = new ArrayList<>();

        Connection myConn = null;
//        PreparedStatement myStmt = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from comment_like where post_id=" + post_id;
//            String sql = "select * from comment_like where post_id=?";
//            myStmt = myConn.prepareStatement(sql);
//            myStmt.setInt(1, comment_id);
//            myRs = myStmt.executeQuery();
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (true) {
                if (myRs.next()) {
                    int id = myRs.getInt("id");
                    boolean like = myRs.getBoolean("like");
                    int comment_id = myRs.getInt("comment_id");
                    int user_id = myRs.getInt("user_id");
                    CommentLike commentLike = new CommentLike(id, like, comment_id, user_id, post_id);
                    commentLikes.add(commentLike);
                }else break;
            }
            return commentLikes;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
    public List<Post> getPosts() throws Exception {
        List<Post> posts = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from post";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String message = myRs.getString("message");
                List<PostLike> postLikes = getPostLikesByPostId(id);
                List<Comment> comments = getCommentsByPostId(id);
                int user_id = myRs.getInt("user_id");
                Post post = new Post(id, message, postLikes, comments, user_id);
                posts.add(post);
            }
            return posts;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
    public List<PostLike> getPostLikes() throws Exception {
        List<PostLike> postLikes = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from post_like";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                boolean like = myRs.getBoolean("like");
                int post_id = myRs.getInt("post_id");
                int user_id = myRs.getInt("user_id");
                PostLike postLike = new PostLike(id, like, post_id, user_id);
                postLikes.add(postLike);
            }
            return postLikes;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
    public List<Comment> getComments() throws Exception {
        List<Comment> comments = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from comment";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String message = myRs.getString("message");
                int post_id = myRs.getInt("post_id");
                int user_id = myRs.getInt("user_id");
                List<CommentLike> commentLikes = getCommentLikesByPostId(id);
                Comment comment = new Comment(id, message, commentLikes, post_id, user_id);
                comments.add(comment);
            }
            return comments;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
    public List<CommentLike> getCommentLikes() throws Exception {
        List<CommentLike> commentLikes = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from comment_like";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                boolean like = myRs.getBoolean("like");
                int comment_id = myRs.getInt("comment_id");
                int user_id = myRs.getInt("user_id");
                int post_id = myRs.getInt("post_id");
                CommentLike commentLike = new CommentLike(id, like, comment_id, user_id, post_id);
                commentLikes.add(commentLike);
            }
            return commentLikes;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
    public boolean isExisting(String contact) throws Exception {
        List<User> users = getUsers();
        for (User user: users) {
            if (user.getContact().equals(contact)) return true;
        }
        return false;
    }

    @Override
    public User getUser(String contact, String password) throws Exception {
        List<User> users = getUsers();
        for (User user: users) {
            if (user.getContact().equals(contact) && user.getPassword().equals(password)) return user;
        }
        return null;
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if(myStmt != null) {
                myStmt.close();
            }

            if(myConn != null) {
                myConn.close(); //it doesn't really close, it only goes back to connection pool to be available for another user
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "insert into users "
                    + "(firstname, lastname, contact, password, dob, gender) "
                    + "values (?, ?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, user.getFirstname());
            myStmt.setString(2, user.getLastname());
            myStmt.setString(3, user.getContact());
            myStmt.setString(4, user.getPassword());
            myStmt.setString(5, user.getDob());
            myStmt.setString(6, user.getGender());
            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }
    }



//    public Student getStudent(int studentId) throws Exception {
//        Student student = null;
//
//        Connection myConn = null;
//        PreparedStatement myStmt = null;
//        ResultSet myRs = null;
//
//        try {
//            myConn = dataSource.getConnection();
//            String sql = "select * from student where id=?";
//            myStmt = myConn.prepareStatement(sql);
//            myStmt.setInt(1, studentId);
//            myRs = myStmt.executeQuery();
//
//            if (myRs.next()) {
//                String firstname = myRs.getString("first_name");
//                String lastname = myRs.getString("last_name");
//                String email = myRs.getString("email");
//
//                student = new Student(studentId, firstname, lastname, email);
//            } else {
//                throw new Exception("Could not find student id; " + studentId);
//            }
//
//            return student;
//        } finally {
//            close(myConn, myStmt, myRs);
//        }
//    }
//
//    public void updateStudent(Student student) throws Exception{
//        Connection myConn = null;
//        PreparedStatement myStmt = null;
//
//        try {
//            myConn = dataSource.getConnection();
//
//            String sql = "update student "
//                    + "set first_name=?, last_name=?, email=? "
//                    + "where id=?";
//
//            myStmt = myConn.prepareStatement(sql);
//
//            myStmt.setString(1, student.getFirstname());
//            myStmt.setString(2, student.getLastname());
//            myStmt.setString(3, student.getEmail());
//            myStmt.setInt(4, student.getId());
//
//            myStmt.execute();
//
//        } finally {
//            close(myConn, myStmt, null);
//        }
//    }
//
//    public void delete(int studentId) throws Exception{
//        Connection myConn = null;
//        PreparedStatement myStmt = null;
//
//        try {
//            myConn = dataSource.getConnection();
//            String sql = "delete from student where id=?";
//            myStmt = myConn.prepareStatement(sql);
//            myStmt.setInt(1, studentId);
//            myStmt.execute();
//
//        } finally {
//            close(myConn, myStmt, null);
//        }
//    }
}
