package com.onlinefoodorder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.dao.CartDao;
import com.onlinefoodorder.model.Cart;
import com.onlinefoodorder.model.Category;


@Controller
public class CartController {

	@Autowired
	private CartDao cartDao;
	
	@GetMapping("/addToCart")
	public ModelAndView addtoCart(@ModelAttribute Cart cart) {
		ModelAndView mv = new ModelAndView();
	
		cartDao.save(cart);
		mv.addObject("status", "Foods added to cart!");
		mv.setViewName("index");
		
		return mv;
	
	}
	
	@GetMapping("/deletecart")
	public ModelAndView deleteProductFromCart(@RequestParam("cartId") int  cartId) {
		ModelAndView mv = new ModelAndView();
		
		Cart cart = new Cart();
	
		Optional<Cart> o = cartDao.findById(cartId);
		if(o.isPresent()) {
			cart = o.get();
		}
		
		cartDao.delete(cart);
		
		mv.addObject("status", "Selected Food removed from Cart!");
		mv.setViewName("index");
		
		return mv;
	}
	
	
	
	

}
//package com.onlinefoodorder.controller;

//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.onlinefoodorder.dao.CartDao;
//import com.onlinefoodorder.model.Cart;
////import com.onlinefoodorder.model.Category;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import lombok.extern.slf4j.Slf4j;
//
//
//@Controller
//@Slf4j
//public class CartController {
//
//	private static final Logger logger = Logger.getLogger(CartController.class.getName());
//
//	@Autowired
//	private CartDao cartDao;
//
//	@GetMapping("/addToCart")
//	public ModelAndView addtoCart(@ModelAttribute Cart cart) {
//		ModelAndView mv = new ModelAndView();
//
//		cartDao.save(cart);
//		logger.log(Level.INFO, "Foods added to cart!");
//		mv.addObject("status", "Foods added to cart!");
//		mv.setViewName("index");
//
//		return mv;
//	}
//
//	@GetMapping("/deletecart")
//	public ModelAndView deleteProductFromCart(@RequestParam("cartId") int cartId) {
//		ModelAndView mv = new ModelAndView();
//
//		Optional<Cart> o = cartDao.findById(cartId);
//		if (o.isPresent()) {
//			Cart cart = o.get();
//			cartDao.delete(cart);
//			logger.log(Level.INFO, "Selected Food removed from Cart!");
//			mv.addObject("status", "Selected Food removed from Cart!");
//		} else {
//			logger.log(Level.WARNING, "Failed to find cart item with ID: {0}", cartId);
//			mv.addObject("status", "Failed to remove food from Cart!");
//		}
//
//		mv.setViewName("index");
//
//		return mv;
//	}
//}
