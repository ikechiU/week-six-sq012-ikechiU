package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.mock_impl.UserDaoMockImpl;
import com.example.week6_project.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDaoImplTest {

    private UserDaoImpl mockUserDao;

    @BeforeEach
    void init() {
        mockUserDao = Mockito.mock(UserDaoImpl.class);
    }

    @DisplayName("Getting Users")
    @Test
    void getUsers() throws Exception {
        when(mockUserDao.getUsers()).thenReturn(new UserDaoMockImpl().getUsers());
        List<User> userList = mockUserDao.getUsers();
        verify(mockUserDao, times(1)).getUsers();
        assertEquals(new UserDaoMockImpl().getUsers().size(), userList.size());
        assertEquals("Ikechi", userList.get(0).getFirstname());
    }

    @DisplayName("Getting User Name by Id")
    @Test
    void getUserNameById() throws Exception {
        when(mockUserDao.getUserNameById(1)).thenReturn(new UserDaoMockImpl().getUserNameById(1));
        User user = mockUserDao.getUserNameById(1);
        verify(mockUserDao, times(1)).getUserNameById(1);
        assertEquals(new UserDaoMockImpl().getUserNameById(1).getContact(), user.getContact());
    }

    @DisplayName("Getting User Name by Id Fail")
    @Test
    void getUserNameById_Fail_ID_Does_Not_Exist() throws Exception {
        when(mockUserDao.getUserNameById(100)).thenReturn(new UserDaoMockImpl().getUserNameById(100));
        User user = mockUserDao.getUserNameById(100);
        verify(mockUserDao, times(1)).getUserNameById(100);
        assertNull(user);
    }
}