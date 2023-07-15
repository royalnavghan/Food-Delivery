package com.onlinefoodorder.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.dao.AdminDao;
import com.onlinefoodorder.model.Admin;

class AdminControllerTest {

    @Mock
    private AdminDao adminDao;

    @InjectMocks
    private AdminController adminController;

    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
    }

    @Test
    void testGoToHomeDuringStart() {
        String result = adminController.goToHomeDuringStart();
        Assertions.assertEquals("index", result);
    }
    
    @Test
    void testGoToHomeDuringStartFail() {
        String result = adminController.goToHomeDuringStart();
        Assertions.assertNotEquals("home", result);
    }

    @Test
    void testGoToHome() {
        String result = adminController.goToHome();
        Assertions.assertEquals("index", result);
    }
    
    @Test
    void testGoToHomeFail() {
        String result = adminController.goToHome();
        Assertions.assertNotEquals("home", result);
    }

    @Test
    void testGoToAdminPage() {
        String result = adminController.goToAdminPage();
        Assertions.assertEquals("admin", result);
    }
    
    @Test
    void testGoToAdminPageFail() {
        String result = adminController.goToAdminPage();
        Assertions.assertNotEquals("home", result);
    }

    @Test
    void testGoToAdminLoginPage() {
        String result = adminController.goToAdminLoginPage();
        Assertions.assertEquals("adminlogin", result);
    }
    
    @Test
    void testGoToAdminLoginPageFail() {
        String result = adminController.goToAdminLoginPage();
        Assertions.assertNotEquals("admin", result);
    }

    @Test
    void testGoToAdminRegisterPage() {
        String result = adminController.goToAdminRegisterPage();
        Assertions.assertEquals("adminregister", result);
    }
    
    @Test
    void testGoToAdminRegisterPageFail() {
        String result = adminController.goToAdminRegisterPage();
        Assertions.assertNotEquals("admin", result);
    }

    @Test
    void testRegisterAdmin() {
        Admin admin = new Admin();
        admin.setFirstname("John");
        admin.setLastname("Doe");
        admin.setEmailid("johndoe@example.com");
        admin.setPassword("password");

        Mockito.when(adminDao.save(admin)).thenReturn(admin);

        ModelAndView result = adminController.registerAdmin(admin);

        Assertions.assertEquals("adminlogin", result.getViewName());
        Assertions.assertEquals("John Successfully Registered as ADMIN", result.getModel().get("status"));
    }

    @Test
    void testRegisterAdminFailed() {
        Admin admin = new Admin();
        admin.setFirstname("John");
        admin.setLastname("Doe");
        admin.setEmailid("johndoe@example.com");
        admin.setPassword("password");

        Mockito.when(adminDao.save(admin)).thenReturn(null);

        ModelAndView result = adminController.registerAdmin(admin);

        Assertions.assertEquals("adminregister", result.getViewName());
        Assertions.assertEquals("John Failed to Registered as ADMIN", result.getModel().get("status"));
    }

    @Test
    void testLoginAdmin() {
        Admin admin = new Admin();
        admin.setFirstname("John");
        admin.setLastname("Doe");
        admin.setEmailid("johndoe@example.com");
        admin.setPassword("password");

        Mockito.when(adminDao.findByEmailidAndPassword("johndoe@example.com", "password")).thenReturn(admin);

        request.setSession(new MockHttpSession());
        ModelAndView result = adminController.loginAdmin(request, "johndoe@example.com", "password");

        Assertions.assertEquals("index", result.getViewName());
        Assertions.assertEquals("John Successfully Logged in as ADMIN!", result.getModel().get("status"));
        Assertions.assertEquals("admin", request.getSession().getAttribute("user-login"));
        Assertions.assertEquals(admin, request.getSession().getAttribute("active-user"));
    }

    @Test
    void testLoginAdminFailed() {
        Mockito.when(adminDao.findByEmailidAndPassword("johndoe@example.com", "password")).thenReturn(null);

        ModelAndView result = adminController.loginAdmin(request, "johndoe@example.com", "password");

        Assertions.assertEquals("index", result.getViewName());
        Assertions.assertEquals("Failed to login as Admin!", result.getModel().get("status"));
        Assertions.assertNull(request.getSession().getAttribute("user-login"));
        Assertions.assertNull(request.getSession().getAttribute("active-user"));
    }

}