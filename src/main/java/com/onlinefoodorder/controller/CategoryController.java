package com.onlinefoodorder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.dao.CategoryDao;
import com.onlinefoodorder.dao.FoodDao;
import com.onlinefoodorder.model.Category;
import com.onlinefoodorder.model.Food;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.dao.CategoryDao;
import com.onlinefoodorder.dao.FoodDao;
import com.onlinefoodorder.model.Category;
import com.onlinefoodorder.model.Food;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
//

@Controller
public class CategoryController {
	
	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@PostMapping("/addcategory")
	public ModelAndView addcategory(@ModelAttribute Category category) {
		ModelAndView  mv = new ModelAndView();
		
		categoryDao.save(category);
		
		mv.addObject("status", "Category Added Successfully!");
		mv.setViewName("index");
		
		return mv;
	}
	
	@GetMapping("/deletecategory")
	public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {
        ModelAndView  mv = new ModelAndView();
		
        Category category = null;
		Optional<Category> o = categoryDao.findById(categoryId);
		
		if(o.isPresent()) {
			category = o.get();
		}
		
		categoryDao.delete(category);
        
		List<Food> foods = foodDao.findByCategoryId(categoryId);
		
		for(Food food : foods) {
			foodDao.delete(food);
		}
		
		mv.addObject("status", "Category Deleted Successfully!");
		mv.setViewName("index");
		
		return mv;
	}
	
	@GetMapping("/category")
	public ModelAndView category(@RequestParam("categoryId") int  categoryId) {
		ModelAndView mv = new ModelAndView();
		List<Food> foods = new ArrayList<>();
		if(categoryId == 0) {
			foods = foodDao.findAll();
		}
		
		else {
			foods = foodDao.findByCategoryId(categoryId);
		}
		
		mv.addObject("foods", foods);
		mv.addObject("sentFromOtherSource", "yes");
		mv.setViewName("index");
		
		return mv;
	}

}
//package com.onlinefoodorder.controller;


//@Controller
//@Slf4j
//public class CategoryController {
//
//	private static final Logger logger = Logger.getLogger(CategoryController.class.getName());
//
//	@Autowired
//	private FoodDao foodDao;
//
//	@Autowired
//	private CategoryDao categoryDao;
//
//	@PostMapping("/addcategory")
//	public ModelAndView addcategory(@ModelAttribute Category category) {
//		ModelAndView mv = new ModelAndView();
//
//		categoryDao.save(category);
//		logger.log(Level.INFO, "Category Added Successfully!");
//		mv.addObject("status", "Category Added Successfully!");
//		mv.setViewName("index");
//
//		return mv;
//	}
//
//	@GetMapping("/deletecategory")
//	public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {
//		ModelAndView mv = new ModelAndView();
//
//		Optional<Category> o = categoryDao.findById(categoryId);
//		if (o.isPresent()) {
//			Category category = o.get();
//			categoryDao.delete(category);
//
//			List<Food> foods = foodDao.findByCategoryId(categoryId);
//
//			for (Food food : foods) {
//				foodDao.delete(food);
//			}
//
//			logger.log(Level.INFO, "Category Deleted Successfully!");
//			mv.addObject("status", "Category Deleted Successfully!");
//		} else {
//			logger.log(Level.WARNING, "Failed to find category with ID: {0}", categoryId);
//			mv.addObject("status", "Failed to delete category!");
//		}
//
//		mv.setViewName("index");
//
//		return mv;
//	}
//
//	@GetMapping("/category")
//	public ModelAndView category(@RequestParam("categoryId") int categoryId) {
//		ModelAndView mv = new ModelAndView();
//		List<Food> foods = new ArrayList<>();
//		if (categoryId == 0) {
//			foods = foodDao.findAll();
//		} else {
//			foods = foodDao.findByCategoryId(categoryId);
//		}
//
//		mv.addObject("foods", foods);
//		mv.addObject("sentFromOtherSource", "yes");
//		mv.setViewName("index");
//
//		return mv;
//	}
//
//}
