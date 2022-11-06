package com.example.week6_project.dao.mock_impl;

import com.example.week6_project.dao.CommentDao;
import com.example.week6_project.model.Comment;
import com.example.week6_project.model.CommentLike;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoMockImpl implements CommentDao {
    @Override
    public List<Comment> getComments() throws Exception {
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment(17, "This is comment one", 4,1, "Ikechi Ucheagwu");
        Comment comment1 = new Comment(18, "This is comment two", 5, 2, "Saint Michael");
        Comment comment2 = new Comment(19, "This is comment three", 6,  3, "Anne Smith");
        Comment comment3 = new Comment(19, "This is comment four", 7,  1, "Ikechi Ucheagwu");
        Comment comment4 = new Comment(20, "This is comment five", 8,  2, "Saint Michael");
        Comment comment5 = new Comment(21, "This is comment six", 9,  3, "Anne Smith");
        Comment comment6 = new Comment(22, "This is comment seven", 5,  1, "Ikechi Ucheagwu");
        Comment comment7 = new Comment(23, "This is comment eight", 4,  2, "Saint Michael");
        Comment comment8 = new Comment(24, "This is comment nine", 7,  3, "Anne Smith");
        comments.add(comment);
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
        comments.add(comment4);
        comments.add(comment5);
        comments.add(comment6);
        comments.add(comment7);
        comments.add(comment8);

        List<CommentLike> commentLikeList = new CommentLikeDaoMockImpl().getCommentLikes();

        for(Comment c : comments) {
            List<CommentLike> commentLikes = new ArrayList<>();
            for (CommentLike commentLike: commentLikeList) {
                if (c.getId() == commentLike.getComment_id()) {
                    commentLikes.add(commentLike);
                }
            }
            c.setCommentLikes(commentLikes);
        }

        return comments;
    }

    @Override
    public List<Comment> getCommentsByPostId(int post_id) throws Exception {
        return null;
    }

    @Override
    public void updateUser(int id, String name) throws SQLException {

    }

    @Override
    public void addComment(String comment, int user_id, int post_id, String name) throws Exception {

    }

    @Override
    public void updateComment(String comment, int comment_id, int user_id, int post_id, String name) throws Exception {

    }

    @Override
    public void deleteComment(int comment_id, int user_id, int post_id) throws Exception {

    }
}
