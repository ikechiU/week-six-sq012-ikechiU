package com.example.week6_project.dao.mock_impl;

import com.example.week6_project.dao.PostDao;
import com.example.week6_project.model.Comment;
import com.example.week6_project.model.Post;
import com.example.week6_project.model.PostLike;
import com.example.week6_project.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDaoMockImpl implements PostDao {
    @Override
    public List<Post> getPosts() throws Exception {
        List<Post> posts = new ArrayList<>();
        Post post = new Post(4, "This is post one", 1, "Ikechi Ucheagwu");
        Post post2 = new Post(5, "This is post two", 2, "Saint Michael");
        Post post3 = new Post(6, "This is post three", 3, "Anne Smith");
        Post post4 = new Post(7, "This is post four", 1, "Ikechi Ucheagwu");
        Post post5 = new Post(8, "This is post five", 2, "Saint Michael");
        Post post6 = new Post(9, "This is post six", 3, "Anne Smith");
        posts.add(post);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
        posts.add(post5);
        posts.add(post6);

        List<PostLike> postLikesList = new PostLikeDaoMockImpl().getPostLikes();
        List<Comment> commentList = new CommentDaoMockImpl().getComments();

        for (Post p : posts) {
            List<PostLike> postLikes = new ArrayList<>();
            List<Comment> comments = new ArrayList<>();
            for (PostLike postLike : postLikesList) {
                if (p.getId() == postLike.getPost_id()) {
                    postLikes.add(postLike);
                }
            }

            for (Comment comment : commentList) {
                if (p.getId() == comment.getPost_id()) {
                    comments.add(comment);
                }
            }
            p.setPostLikes(postLikes);
            p.setComments(comments);
        }
        return posts;
    }

    @Override
    public List<Post> getPostsByUserId(int user_id) throws Exception {
        List<Post> posts = new ArrayList<>();
        for(Post post : getPosts()) {
            if (post.getUser_id() == user_id) {
                posts.add(post);
            }
        }
        return posts;
    }

    @Override
    public String updateUser(int id, String name) throws Exception {
        for(Post post : getPosts()) {
            if (post.getUser_id() == id) {
                post.setName(name);
                System.out.println(name);
                return name;
            }
        }
        System.out.println("User name not updated: name is same");
        return null;
    }

    @Override
    public List<Post> addPost(String message, int user_id, String name) throws Exception {
        List<Post> postList = getPosts();
        Post post = new Post((int) System.currentTimeMillis(), "A new post for user 3", 3, "Anne Smith");
        postList.add(post);
        System.out.println("Post size after insertion: " + postList.size());
        return postList;
    }

    @Override
    public List<Post> delete(int user_id, int post_id) throws Exception {
        List<Post> postList = getPosts();
        int postNo = -1;
        for (int i = 0; i < postList.size(); i++) {
            Post post = postList.get(i);
            if (post.getUser_id() == user_id && post.getId() == post_id) {
                postNo = i;
                break;
            }
        }

        if (postNo != -1) {
            postList.remove(postNo);
        }
        System.out.println("Post size after delete: " + postList.size());
        return postList;
    }

    @Override
    public List<Post> update(int user_id, int post_id, String message) throws Exception {
        List<Post> postList = getPosts();
        int postNo = -1;
        for (int i = 0; i < postList.size(); i++) {
            Post post = postList.get(i);
            if (post.getUser_id() == user_id && post.getId() == post_id) {
                postNo = i;
                break;
            }
        }

        if (postNo != -1) {
            Post post = postList.get(postNo);
            post.setMessage(message);
            postList.set(postNo, post);
        }

        System.out.println(postList.get(postNo).getMessage());
        return postList;
    }

    @Override
    public List<Post> like(int user_id, int post_id) throws Exception {
        List<Post> postList = getPosts();
        List<PostLike> postLikes = new ArrayList<>();
        int postNo = -1;
        for (int i = 0; i < postList.size(); i++) {
            Post post = postList.get(i);
            if (post.getId() == post_id) {
                postLikes = post.getPostLikes();
                postNo = i;
                break;
            }
        }

        boolean isUpdated = false;
        for (int i = 0; i < postLikes.size(); i++) {
            PostLike postLike = postLikes.get(i);
            if (postLike.getPost_id() == post_id && postLike.getUser_id() == user_id) {
                isUpdated = true;
                boolean like = postLike.isLike();
                postLike.setLike(!like);
                postLikes.set(i, postLike);
                break;
            }
        }

        if (!isUpdated) {
            PostLike postLike = new PostLike((int) System.currentTimeMillis(), true, post_id, user_id);
            postLikes.add(postLike);
        }

        System.out.println(postLikes.size() + ": " + postLikes);

        if (postNo != -1) {
            Post post = postList.get(postNo);
            post.setPostLikes(postLikes);
            postList.set(postNo, post);
        }

        return postList;
    }
}
