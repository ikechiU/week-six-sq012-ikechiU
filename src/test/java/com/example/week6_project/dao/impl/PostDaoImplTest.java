package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.PostDao;
import com.example.week6_project.dao.mock_impl.PostDaoMockImpl;
import com.example.week6_project.model.Post;
import com.example.week6_project.model.PostLike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostDaoImplTest {

    PostDaoImpl mockPostDao;
    PostDaoMockImpl postDaoMock;

    @BeforeEach
    void init() {
        mockPostDao = Mockito.mock(PostDaoImpl.class);
        postDaoMock = new PostDaoMockImpl();
    }

    @Test
    void getPosts() throws Exception {
        System.out.println(postDaoMock.getPosts());
        when(mockPostDao.getPosts()).thenReturn(postDaoMock.getPosts());
        List<Post> posts = mockPostDao.getPosts();
        verify(mockPostDao, times(1)).getPosts();
        assertEquals(6, posts.size());
    }

    @Test
    void getPostsByUserId() throws Exception {
        System.out.println(postDaoMock.getPosts().get(0));
        System.out.println(postDaoMock.getPosts().get(3));
        when(mockPostDao.getPostsByUserId(1)).thenReturn(postDaoMock.getPostsByUserId(1));
        List<Post> posts = mockPostDao.getPostsByUserId(1);
        verify(mockPostDao, times(1)).getPostsByUserId(1);
        assertEquals(2, posts.size());
    }

    @Test
    void updateUser() throws Exception {
        System.out.println(postDaoMock.getPosts().get(0).getName()); //Post(4, "This is post one", 1, "Ikechi Ucheagwu");
        when(mockPostDao.updateUser(1, "A new name")).thenReturn(postDaoMock.updateUser(1, "A new name"));
        String result = mockPostDao.updateUser(1, "A new name");
        verify(mockPostDao, times(1)).updateUser(1, "A new name");
        assertEquals("A new name", result);
    }

    @Test
    void updateUser_Fail() throws Exception {
        System.out.println(postDaoMock.getPosts().get(0).getName()); //Post(4, "This is post one", 1, "Ikechi Ucheagwu");
        when(mockPostDao.updateUser(1, "A new name")).thenReturn(postDaoMock.updateUser(100, "A new name"));
        String result = mockPostDao.updateUser(100, "A new name");
        verify(mockPostDao, times(1)).updateUser(100, "A new name");
        assertNull(result);
    }

    @Test
    void addPost() throws Exception {
        System.out.println("Initial post size: " + postDaoMock.getPosts().size());
        when(mockPostDao.addPost("This is post is update", 1, "Ikechi Ucheagwu"))
                .thenReturn(postDaoMock.addPost("This is post is update", 1, "Ikechi Ucheagwu"));

        List<Post> posts = mockPostDao.addPost("This is post is update", 1, "Ikechi Ucheagwu");

        verify(mockPostDao, times(1)).addPost("This is post is update", 1, "Ikechi Ucheagwu");
        assertEquals(7, posts.size());
    }


    @Test
    void delete() throws Exception {
        System.out.println("Initial post size: " + postDaoMock.getPosts().size());
        when(mockPostDao.delete(1, 4))
                .thenReturn(postDaoMock.delete(1, 4));

        List<Post> posts = mockPostDao.delete(1, 4);

        verify(mockPostDao, times(1)).delete(1, 4);
        assertEquals(5, posts.size());
    }

    @Test
    void delete_With_Wrong_Post_Id() throws Exception {
        System.out.println("Initial post size: " + postDaoMock.getPosts().size());
        when(mockPostDao.delete(1, 100))
                .thenReturn(postDaoMock.delete(1, 100));

        List<Post> posts = mockPostDao.delete(1, 100);

        verify(mockPostDao, times(1)).delete(1, 100);
        assertEquals(6, posts.size());
    }

    @Test
    void delete_With_Wrong_User_Id() throws Exception {
        System.out.println("Initial post size: " + postDaoMock.getPosts().size());
        when(mockPostDao.delete(4, 4))
                .thenReturn(postDaoMock.delete(4, 4));

        List<Post> posts = mockPostDao.delete(4, 4);

        verify(mockPostDao, times(1)).delete(4, 4);
        assertEquals(6, posts.size());
    }


    @Test
    void update() throws Exception {
        System.out.println(postDaoMock.getPosts().get(0).getMessage()); //Post(4, "This is post one", 1, "Ikechi Ucheagwu");
        when(mockPostDao.update(1, 4, "This post is updated"))
                .thenReturn(postDaoMock.update(1, 4, "This post is updated" ));
        List<Post> posts = mockPostDao.update(1, 4, "This post is updated");
        verify(mockPostDao, times(1)).update(1, 4, "This post is updated");
        assertEquals(posts.get(0).getMessage(), "This post is updated");
    }

    @Test
    void like() throws Exception {
        System.out.println(postDaoMock.getPosts().get(5).getPostLikes().size() + ": " + postDaoMock.getPosts().get(5).getPostLikes());
        when(mockPostDao.like(3, 9)).thenReturn(postDaoMock.like(3, 9));
        List<Post> posts = mockPostDao.like(3, 9);
        verify(mockPostDao, times(1)).like(3, 9);

        List<PostLike> postLikes = new ArrayList<>();
        for (Post post: posts) {
            if (post.getId() == 9) {
                postLikes = post.getPostLikes();
            }
        }

        int count = 0;
        for (PostLike postLike : postLikes) {
            if (postLike.isLike()) count++;
        }

        assertEquals(2, count);
    }

    @DisplayName("User 2 unlikes post 4")
    @Test
    void unlike() throws Exception {
        System.out.println(postDaoMock.getPosts().get(0).getPostLikes().size() + ": " + postDaoMock.getPosts().get(0).getPostLikes());
        when(mockPostDao.like(2, 4)).thenReturn(postDaoMock.like(2, 4));
        List<Post> posts = mockPostDao.like(2, 4);
        verify(mockPostDao, times(1)).like(2, 4);

        List<PostLike> postLikes = new ArrayList<>();
        for (Post post: posts) {
            if (post.getId() == 4) {
                postLikes = post.getPostLikes();
            }
        }

        int count = 0;
        for (PostLike postLike : postLikes) {
            if (postLike.isLike()) count++;
        }

        assertEquals(1, count);
    }
}