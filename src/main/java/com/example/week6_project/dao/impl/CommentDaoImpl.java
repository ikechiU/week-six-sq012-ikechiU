package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.CommentDao;
import com.example.week6_project.dao.shared.ProvideConnection;
import com.example.week6_project.model.Comment;
import com.example.week6_project.model.CommentLike;
import com.example.week6_project.model.UserData;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.week6_project.dao.shared.DbUtils.close;

public class CommentDaoImpl implements CommentDao {

    private final DataSource dataSource = ProvideConnection.dataSource();

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
                String name = myRs.getString("name");
                List<CommentLike> commentLikes = new CommentLikeDaoImpl().getCommentLikesByCommentId(id);
                Comment comment = new Comment(id, message, commentLikes, post_id, user_id, name);
                comments.add(comment);
            }
            return comments;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
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
                    String name = myRs.getString("name");
                    Comment comment = new Comment(id, message, post_id, user_id, name);
                    comments.add(comment);
                }else break;
            }

            for (Comment comment: comments) {
                List<CommentLike> commentLikes = new CommentLikeDaoImpl().getCommentLikesByCommentId(comment.getId());
                comment.setCommentLikes(commentLikes);
            }
            return comments;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    @Override
    public void updateUser(int id, String name) throws SQLException {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "update comment set name=? where user_id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, name);
            myStmt.setInt(2, id);
            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }
    }

    @Override
    public void addComment(String comment, int user_id, int post_id, String name) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "insert into comment "
                    + "(id, message, post_id, user_id, `name`) "
                    + "values (?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, (int) System.currentTimeMillis());
            myStmt.setString(2, comment);
            myStmt.setInt(3, post_id);
            myStmt.setInt(4, user_id);
            myStmt.setString(5, name);
            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }
    }

    @Override
    public void updateComment(String comment, int comment_id, int user_id, int post_id, String name) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "update comment set message=? where post_id=? and user_id=? and id=?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, comment);
            myStmt.setInt(2, post_id);
            myStmt.setInt(3, user_id);
            myStmt.setInt(4, comment_id);
            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }
    }

    @Override
    public void deleteComment(int comment_id, int user_id, int post_id) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "delete from comment where user_id=? and post_id=? and id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, user_id);
            myStmt.setInt(2, post_id);
            myStmt.setInt(3, comment_id);
            myStmt.execute();

        } finally {
            close(myConn, myStmt, null);
        }
    }


}
