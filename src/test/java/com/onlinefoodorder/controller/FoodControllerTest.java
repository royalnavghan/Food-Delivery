package com.onlinefoodorder.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.dao.FoodDao;
import com.onlinefoodorder.model.Food;

class FoodControllerTest {

    @Mock
    private FoodDao foodDao;

    @InjectMocks
    private FoodController foodController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testAddFood() throws ServletException, IOException {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpSession session = new MockHttpSession();
//        request.setSession(session);
//
//        Part part = mock(Part.class);
//        when(part.getSubmittedFileName()).thenReturn("test.jpg");
//        InputStream inputStream = mock(InputStream.class);
//        when(part.getInputStream()).thenReturn(inputStream);
//
//        request.setParameter("name", "Test Food");
//        request.setParameter("description", "Test Description");
//        request.setParameter("price", "10.0");
//        request.setParameter("discount", "5.0");
//        request.setParameter("categoryId", "1");
//        request.setParameter("imagePath", "31.JPG");
//
//        ModelAndView mv = foodController.addProduct(request, session);
//
//        verify(foodDao, times(1)).save(any(Food.class));
//
//        assertEquals("index", mv.getViewName());
//        assertEquals("Food Added Successfully.", mv.getModel().get("status"));
//    }

//    @Test
//    void testUpdateProduct() throws ServletException, IOException {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpSession session = new MockHttpSession();
//        request.setSession(session);
//
//        Part part = mock(Part.class);
//        when(part.getSubmittedFileName()).thenReturn("test.jpg");
//        InputStream inputStream = mock(InputStream.class);
//        when(part.getInputStream()).thenReturn(inputStream);
//
//        request.setParameter("id", "1");
//        request.setParameter("name", "Test Food");
//        request.setParameter("description", "Test Description");
//        request.setParameter("price", "10.0");
//        request.setParameter("discount", "5.0");
//        request.setParameter("categoryId", "1");
//        request.setParameter("image", "31.JPG");
//
//        ModelAndView mv = foodController.updateProduct(request, session);
//
//        verify(foodDao, times(1)).save(any(Food.class));
//
//        assertEquals("index", mv.getViewName());
//        assertEquals("Food updated Successfully.", mv.getModel().get("status"));
//    }

    @Test
    void testSearchFoodByName() throws IOException, ServletException {
        String foodName = "Test Food";
        List<Food> foods = new ArrayList<>();
        foods.add(new Food());
        foods.add(new Food());

        when(foodDao.findByNameContainingIgnoreCase(foodName)).thenReturn(foods);

        ModelAndView mv = foodController.searchProductByName(foodName);

        verify(foodDao, times(1)).findByNameContainingIgnoreCase(foodName);

        assertEquals("index", mv.getViewName());
        assertEquals("yes", mv.getModel().get("sentFromOtherSource"));
        assertEquals(foods, mv.getModel().get("foods"));
    }

    @Test
    void testGetFood() throws IOException, ServletException {
        int foodId = 1;
        Food food = new Food();
        food.setId(foodId);

        when(foodDao.findById(foodId)).thenReturn(Optional.of(food));

        ModelAndView mv = foodController.getFood(foodId);

        verify(foodDao, times(1)).findById(foodId);

        assertEquals("food", mv.getViewName());
        assertEquals(food, mv.getModel().get("food"));
    }

    @Test
    void testDeleteFood() throws IOException, ServletException {
        int foodId = 1;
        Food food = new Food();
        food.setId(foodId);

        when(foodDao.findById(foodId)).thenReturn(Optional.of(food));

        ModelAndView mv = foodController.deleteFood(foodId);

        verify(foodDao, times(1)).delete(food);

        assertEquals("index", mv.getViewName());
        assertEquals("Food Deleted Successfully!", mv.getModel().get("status"));
    }

    @Test
    void testUpdateFood() throws IOException, ServletException {
        int foodId = 1;
        Food food = new Food();
        food.setId(foodId);

        when(foodDao.findById(foodId)).thenReturn(Optional.of(food));

        ModelAndView mv = foodController.updateFood(foodId);

        verify(foodDao, times(1)).findById(foodId);

        assertEquals("updatefood", mv.getViewName());
        assertEquals(food, mv.getModel().get("food"));
    }

}
