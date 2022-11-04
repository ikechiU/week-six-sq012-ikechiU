package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.CommentLikeDao;
import com.example.week6_project.model.CommentLike;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.week6_project.dao.shared.DbUtils.close;

public class CommentLikeDaoImpl implements CommentLikeDao {

    private final DataSource dataSource;

    public CommentLikeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CommentLike> getCommentLikesByCommentId(int comment_id) throws Exception{
        List<CommentLike> commentLikes = new ArrayList<>();

        Connection myConn = null;
//        PreparedStatement myStmt = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from comment_like where comment_id=" + comment_id;
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
                    int post_id = myRs.getInt("post_id");
                    int user_id = myRs.getInt("user_id");
                    if (like) {
                        CommentLike commentLike = new CommentLike(id, true, comment_id, user_id, post_id);
                        commentLikes.add(commentLike);
                    }
                }else break;
            }
            return commentLikes;
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
    public void like(int comment_id, int user_id, int post_id) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from comment_like where user_id=? and post_id=? and comment_id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, user_id);
            myStmt.setInt(2, post_id);
            myStmt.setInt(3, comment_id);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                boolean like = myRs.getBoolean("like");
                String sqlUpdate = "update comment_like set `like`=? where user_id=? and post_id=? and comment_id=?";
                myStmt = myConn.prepareStatement(sqlUpdate);
                myStmt.setBoolean(1, !like);
                myStmt.setInt(2, user_id);
                myStmt.setInt(3, post_id);
                myStmt.setInt(4, comment_id);
            } else {
                String sqlInsert = "insert into comment_like (id, `like`, comment_id, user_id, post_id) values (?, ?, ?, ?, ?)";
                myStmt = myConn.prepareStatement(sqlInsert);
                myStmt.setInt(1, (int) System.currentTimeMillis());
                myStmt.setBoolean(2, true);
                myStmt.setInt(3, comment_id);
                myStmt.setInt(4, user_id);
                myStmt.setInt(5, post_id);
            }
            myStmt.execute();

        } finally {
            close(myConn, myStmt, myRs);
        }
    }
}
