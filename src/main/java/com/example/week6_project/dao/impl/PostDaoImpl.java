package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.PostDao;
import com.example.week6_project.dao.shared.ProvideConnection;
import com.example.week6_project.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.week6_project.dao.shared.DbUtils.close;

public class PostDaoImpl implements PostDao {
    private final DataSource dataSource = ProvideConnection.dataSource();

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
                List<PostLike> postLikes = new PostLikeDaoImpl().getPostLikesByPostId(id);
                List<Comment> comments = new CommentDaoImpl().getCommentsByPostId(id);
                int user_id = myRs.getInt("user_id");
                String name = myRs.getString("name");
                Post post = new Post(id, message, postLikes, comments, user_id, name);
                posts.add(post);
            }
            return posts;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
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
                    List<PostLike> postLikes = new PostLikeDaoImpl().getPostLikesByPostId(id);
                    List<Comment> comments = new CommentDaoImpl().getCommentsByPostId(id);
                    String message = myRs.getString("message");
                    String name = myRs.getString("name");
                    Post post = new Post(id, message, postLikes, comments,  user_id, name);
                    posts.add(post);
                } else break;
            }

            return posts;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
    public String updateUser(int id, String name) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "update post set name=? where user_id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, name);
            myStmt.setInt(2, id);
            boolean result = myStmt.execute();

            if (result) return name;
            else return null;

        } finally {
            close(myConn, myStmt, null);
        }
    }

    @Override
    public List<Post> addPost(String message, int user_id, String name) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "insert into post "
                    + "(id, message, user_id, `name`) "
                    + "values (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, (int) System.currentTimeMillis());
            myStmt.setString(2, message);
            myStmt.setInt(3, user_id);
            myStmt.setString(4, name);
            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }

        return getPosts();
    }

    @Override
    public List<Post> delete(int user_id, int post_id) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "delete from post where user_id=? and id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, user_id);
            myStmt.setInt(2, post_id);
            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }

        return getPosts();
    }

    @Override
    public List<Post> update(int user_id, int post_id, String message) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "update post set message=? where user_id=? and id=?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, message);
            myStmt.setInt(2, user_id);
            myStmt.setInt(3, post_id);
            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }

        return getPosts();
    }

    @Override
    public List<Post> like(int user_id, int post_id) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from post_like where user_id=? and post_id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, user_id);
            myStmt.setInt(2, post_id);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                boolean like = myRs.getBoolean("like");
                String sqlUpdate = "update post_like set `like`=? where user_id=? and post_id=?";
                myStmt = myConn.prepareStatement(sqlUpdate);
                myStmt.setBoolean(1, !like);
                myStmt.setInt(2, user_id);
                myStmt.setInt(3, post_id);
            } else {
                String sqlInsert = "insert into post_like (id, `like`, post_id, user_id) values (?, ?, ?, ?)";
                myStmt = myConn.prepareStatement(sqlInsert);
                myStmt.setInt(1, (int) System.currentTimeMillis());
                myStmt.setBoolean(2, true);
                myStmt.setInt(3, post_id);
                myStmt.setInt(4, user_id);
            }
            myStmt.execute();
        } finally {
            close(myConn, myStmt, myRs);
        }

        return getPosts();
    }


}
