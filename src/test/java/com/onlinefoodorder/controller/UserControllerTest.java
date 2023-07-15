package com.onlinefoodorder.controller;

import com.onlinefoodorder.dao.UserDao;
import com.onlinefoodorder.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGoToLoginPage() {
        String viewName = userController.goToLoginPage();
        assertEquals("userlogin", viewName);
    }

    @Test
    void testGoToRegisterPage() {
        String viewName = userController.goToRegisterPage();
        assertEquals("userregister", viewName);
    }

    @Test
    void testLogout() {
        HttpSession session = new MockHttpSession();
        ModelAndView mv = userController.logout(session);

        assertEquals("index", mv.getViewName());
        assertEquals("Logged out Successfully", mv.getModel().get("status"));
        assertNull(session.getAttribute("active-user"));
        assertNull(session.getAttribute("user-login"));
    }

    @Test
    void testRegisterAdmin() {
        User user = new User();
        user.setFirstname("John");

        when(userDao.save(any(User.class))).thenReturn(user);

        ModelAndView mv = userController.registerAdmin(user);

        verify(userDao, times(1)).save(any(User.class));

        assertEquals("userlogin", mv.getViewName());
        assertEquals("John Successfully Registered!", mv.getModel().get("status"));
    }

    @Test
    void testForgetPassword() {
        ModelAndView mv = userController.forgetpassword("test@example.com", "newpassword", "1234567890");

        verify(userDao, times(1)).findByEmailidAndMobileno("test@example.com", "1234567890");

        assertEquals("userregister", mv.getViewName());
        assertEquals("No User found!",mv.getModel().get("status"));
    }

    @Test
    void testLoginAdmin_Success() {
        HttpServletRequest request = new MockHttpServletRequest();
        User user = new User();
        user.setFirstname("John");

        when(userDao.findByEmailidAndPassword("test@example.com", "password")).thenReturn(user);

        ModelAndView mv = userController.loginAdmin(request, "test@example.com", "password");

        verify(userDao, times(1)).findByEmailidAndPassword("test@example.com", "password");

        HttpSession session = request.getSession();
        assertEquals("index", mv.getViewName());
        assertEquals("John Successfully Logged In!", mv.getModel().get("status"));
        assertEquals(user, session.getAttribute("active-user"));
        assertEquals("user", session.getAttribute("user-login"));
    }

    @Test
    void testLoginAdmin_Failure() {
        HttpServletRequest request = new MockHttpServletRequest();

        when(userDao.findByEmailidAndPassword("test@example.com", "password")).thenReturn(null);

        ModelAndView mv = userController.loginAdmin(request, "test@example.com", "password");

        verify(userDao, times(1)).findByEmailidAndPassword("test@example.com", "password");

        HttpSession session = request.getSession();
        assertEquals("index", mv.getViewName());
        assertEquals("Failed to login!", mv.getModel().get("status"));
        assertNull(session.getAttribute("active-user"));
        assertNull(session.getAttribute("user-login"));
    }
}
