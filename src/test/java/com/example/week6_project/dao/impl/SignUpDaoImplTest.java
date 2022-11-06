package com.example.week6_project.dao.impl;

import com.example.week6_project.dao.SignUpDao;
import com.example.week6_project.dao.mock_impl.SignUpDaoMockImpl;
import com.example.week6_project.dao.mock_impl.UserDaoMockImpl;
import com.example.week6_project.model.User;
import com.example.week6_project.model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignUpDaoImplTest {

    private SignUpDao mockSignUpDao;
    private SignUpDaoMockImpl signUpDaoMock;

    @BeforeEach
    void init() {
        mockSignUpDao = Mockito.mock(SignUpDaoImpl.class);
        signUpDaoMock = new SignUpDaoMockImpl();
    }

    @DisplayName("Add user to Mock DB")
    @Test
    void addUser() throws Exception {
        System.out.println("DB size is: " + new UserDaoMockImpl().getUsers().size());

        User user = new User("Olayinka", "Suleiman", "yinka@decagon.dev", "MTIzNDU2", "12-10-1991","Male");
        when(mockSignUpDao.addUser(user)).thenReturn(signUpDaoMock.addUser(user));

        Optional<User> optionalUser = mockSignUpDao.addUser(user);

        verify(mockSignUpDao, times(1)).addUser(user);
        assertTrue(optionalUser.isPresent());
        assertNotNull(optionalUser.get());
        assertNotEquals(0, optionalUser.get().getId());
    }

    @DisplayName("Add user fail when contact exists")
    @Test
    void addUserFail_When_Contact_Exist() throws Exception {
        System.out.println("DB size is: " + new UserDaoMockImpl().getUsers().size());

        User user = new User("Olayinka", "Suleiman", "ikechi@gmail.com", "MTIzNDU2", "12-10-1991","Male");
        when(mockSignUpDao.addUser(user)).thenReturn(signUpDaoMock.addUser(user));

        Optional<User> optionalUser = mockSignUpDao.addUser(user);

        verify(mockSignUpDao, times(1)).addUser(user);
        assertFalse(optionalUser.isPresent());
        assertThrows(NoSuchElementException.class, optionalUser::get, "Option get method throws exception");
    }

    @Test
    void isExisting() throws Exception {
        String contact = "ikechi@gmail.com";
        String contact2 = "yinka@gmail.com";

        when(mockSignUpDao.isExisting(contact)).thenReturn(signUpDaoMock.isExisting(contact));
        when(mockSignUpDao.isExisting(contact2)).thenReturn(signUpDaoMock.isExisting(contact2));

        boolean result = mockSignUpDao.isExisting(contact);
        boolean result2 = mockSignUpDao.isExisting(contact2);

        verify(mockSignUpDao, times(1)).isExisting(contact);
        verify(mockSignUpDao, times(1)).isExisting(contact2);

        assertTrue(result);
        assertFalse(result2);
    }

    @DisplayName("Update User")
    @Test
    void updateUser() throws Exception {
        User user = new UserDaoMockImpl().getUsers().get(0);
        System.out.println(user); //1, "Ikechi", "Ucheagwu", "08067994494", "MTIzNDU2", "12-10-1985","Male"
        User u  = new User(1, "Happy", "Smith", "09012345678", "123456", "12-12-2012","Male");

        when(mockSignUpDao.updateUser(u)).thenReturn(signUpDaoMock.updateUser(u));
        UserData userData = mockSignUpDao.updateUser(u);

        verify(mockSignUpDao, times(1)).updateUser(u);
        assertEquals(1, userData.getUser().getId());
        assertEquals("MTIzNDU2", userData.getUser().getPassword());
        assertEquals("12-12-2012", userData.getUser().getDob());

    }

    @DisplayName("Update User Fail")
    @Test
    void updateUserFail_ID_Does_Not_Exist() throws Exception {
        User user = new UserDaoMockImpl().getUsers().get(0);
        System.out.println(user); //1, "Ikechi", "Ucheagwu", "08067994494", "MTIzNDU2", "12-10-1985","Male"
        User u  = new User(100, "Happy", "Smith", "09012345678", "123456", "12-12-2012","Male");

        when(mockSignUpDao.updateUser(u)).thenReturn(signUpDaoMock.updateUser(u));
        UserData userData = mockSignUpDao.updateUser(u);

        verify(mockSignUpDao, times(1)).updateUser(u);
        assertNull(userData);
    }
}