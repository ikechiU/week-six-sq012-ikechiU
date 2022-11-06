package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.mock_impl.LoginDaoMockImpl;
import com.example.week6_project.model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginDaoImplTest {

    LoginDaoImpl mockLoginDao;

    @BeforeEach
    void init() {
        mockLoginDao = Mockito.mock(LoginDaoImpl.class);
    }

    @DisplayName("Get UserData object")
    @Test
    void getUserData() throws Exception {
        when(mockLoginDao.getUserData("ikechi@gmail.com", "123456"))
                .thenReturn(new LoginDaoMockImpl().getUserData("ikechi@gmail.com", "123456"));

        UserData userData = mockLoginDao.getUserData("ikechi@gmail.com", "123456");
        verify(mockLoginDao, times(1)).getUserData("ikechi@gmail.com", "123456");

        LoginDaoMockImpl loginDaoMock = new LoginDaoMockImpl();
        UserData dbData = loginDaoMock.getUserData("ikechi@gmail.com", "123456");
        assertEquals(dbData.getUser().getId(), userData.getUser().getId());
        assertEquals(dbData.getUser().getFirstname(), userData.getUser().getFirstname());
        assertEquals(dbData.getUser().getPassword(), userData.getUser().getPassword());
        assertEquals(dbData.getUser().getGender(), userData.getUser().getGender());
        assertEquals(dbData.getUser().getDob(), userData.getUser().getDob());
    }

    @DisplayName("UserData object is null")
    @Test
    void getUserData_Null()throws Exception {
        when(mockLoginDao.getUserData("ikechi@gmail.com", "12345"))
                .thenReturn(new LoginDaoMockImpl().getUserData("ikechi@gmail.com", "12345"));

        UserData userData = mockLoginDao.getUserData("ikechi@gmail.com", "12345");
        verify(mockLoginDao, times(1)).getUserData("ikechi@gmail.com", "12345");

        assertNull(userData);
    }
}