package com.onlinefoodorder.controller;

import com.onlinefoodorder.dao.CategoryDao;
import com.onlinefoodorder.dao.FoodDao;
import com.onlinefoodorder.model.Category;
import com.onlinefoodorder.model.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private FoodDao foodDao;

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory() {
        Category category = new Category();
        category.setName("Test Category");

        ModelAndView mv = categoryController.addcategory(category);

        verify(categoryDao, times(1)).save(category);

        assertEquals("index", mv.getViewName());
        assertEquals("Category Added Successfully!", mv.getModel().get("status"));
    }

    @Test
    void testDeleteCategory() {
        int categoryId = 1;
        Category category = new Category();
        category.setId(categoryId);

        when(categoryDao.findById(categoryId)).thenReturn(Optional.of(category));

        List<Food> foods = new ArrayList<>();
        foods.add(new Food());
        foods.add(new Food());

        when(foodDao.findByCategoryId(categoryId)).thenReturn(foods);

        ModelAndView mv = categoryController.deleteCategory(categoryId);

        verify(categoryDao, times(1)).delete(category);
        verify(foodDao, times(2)).delete(any(Food.class));

        assertEquals("index", mv.getViewName());
        assertEquals("Category Deleted Successfully!", mv.getModel().get("status"));
    }

    @Test
    void testCategory_AllFoods() {
        int categoryId = 0;
        List<Food> foods = new ArrayList<>();
        foods.add(new Food());
        foods.add(new Food());

        when(foodDao.findAll()).thenReturn(foods);

        ModelAndView mv = categoryController.category(categoryId);

        verify(foodDao, times(1)).findAll();

        assertEquals("index", mv.getViewName());
        assertEquals(foods, mv.getModel().get("foods"));
        assertEquals("yes", mv.getModel().get("sentFromOtherSource"));
    }

    @Test
    void testCategory_ByCategoryId() {
        int categoryId = 1;
        List<Food> foods = new ArrayList<>();
        foods.add(new Food());

        when(foodDao.findByCategoryId(categoryId)).thenReturn(foods);

        ModelAndView mv = categoryController.category(categoryId);

        verify(foodDao, times(1)).findByCategoryId(categoryId);

        assertEquals("index", mv.getViewName());
        assertEquals(foods, mv.getModel().get("foods"));
        assertEquals("yes", mv.getModel().get("sentFromOtherSource"));
    }


}
