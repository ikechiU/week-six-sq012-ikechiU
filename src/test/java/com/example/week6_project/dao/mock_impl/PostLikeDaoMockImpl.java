package com.example.week6_project.dao.mock_impl;

import com.example.week6_project.dao.PostLikeDao;
import com.example.week6_project.model.PostLike;

import java.util.ArrayList;
import java.util.List;

public class PostLikeDaoMockImpl implements PostLikeDao {
    @Override
    public List<PostLike> getPostLikes() throws Exception {
        List<PostLike> postLikes = new ArrayList<>();
        PostLike postLike = new PostLike(10, true, 4, 1);
        PostLike postLike2 = new PostLike(11, true, 5, 1);
        PostLike postLike3 = new PostLike(12, false, 6, 2);
        PostLike postLike4 = new PostLike(13, true, 7, 3);
        PostLike postLike5 = new PostLike(14, false, 8, 3);
        PostLike postLike6 = new PostLike(15, true, 9, 1);
        PostLike postLike7 = new PostLike(16, true, 4, 2);

        postLikes.add(postLike);
        postLikes.add(postLike2);
        postLikes.add(postLike3);
        postLikes.add(postLike4);
        postLikes.add(postLike5);
        postLikes.add(postLike6);
        postLikes.add(postLike7);
        return postLikes;
    }

    @Override
    public List<PostLike> getPostLikesByPostId(int post_id) throws Exception {
        return null;
    }
}
