package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.PostLikeDao;
import com.example.week6_project.dao.shared.ProvideConnection;
import com.example.week6_project.model.PostLike;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.week6_project.dao.shared.DbUtils.close;

public class PostLikeDaoImpl implements PostLikeDao {

    private final DataSource dataSource = ProvideConnection.dataSource();

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
                    if (like) {
                        PostLike postLike = new PostLike(id, true, nPost_id, user_id);
                        postLikes.add(postLike);
                    }
                } else break;
            }

            return postLikes;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

}
