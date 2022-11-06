package com.example.week6_project.dao.mock_impl;

import com.example.week6_project.dao.CommentLikeDao;
import com.example.week6_project.model.CommentLike;

import java.util.ArrayList;
import java.util.List;

public class CommentLikeDaoMockImpl implements CommentLikeDao {
    @Override
    public List<CommentLike> getCommentLikesByCommentId(int post_id) throws Exception {
        return null;
    }

    @Override
    public List<CommentLike> getCommentLikes() throws Exception {
        List<CommentLike> commentLikes = new ArrayList<>();
        CommentLike commentLike = new CommentLike(25, true, 17, 1, 4);
        CommentLike commentLike2 = new CommentLike(26, false, 18, 2, 5);
        CommentLike commentLike3 = new CommentLike(27, true, 19, 3, 6);
        CommentLike commentLike4 = new CommentLike(28, false, 20, 1, 7);
        CommentLike commentLike5 = new CommentLike(29, true, 21, 2, 8);
        CommentLike commentLike6 = new CommentLike(30, true, 22, 3, 9);
        CommentLike commentLike7 = new CommentLike(31, false, 23, 1, 4);
        CommentLike commentLike8 = new CommentLike(32, true, 24, 2, 5);
        commentLikes.add(commentLike);
        commentLikes.add(commentLike2);
        commentLikes.add(commentLike3);
        commentLikes.add(commentLike4);
        commentLikes.add(commentLike5);
        commentLikes.add(commentLike6);
        commentLikes.add(commentLike7);
        commentLikes.add(commentLike8);
        return commentLikes;
    }

    @Override
    public void like(int comment_id, int user_id, int post_id) throws Exception {

    }
}
