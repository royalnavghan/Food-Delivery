package com.onlinefoodorder.controller;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.dao.CartDao;
import com.onlinefoodorder.model.Cart;

class CartControllerTest {

    @Mock
    private CartDao cartDao;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddtoCart() {
        Cart cart = new Cart();
        cart.setId(1);
        cart.setUserId(1);
        cart.setDate("2023-05-13");
        cart.setQuantity(2);
        cart.setFoodId(2);

        ModelAndView result = cartController.addtoCart(cart);

        Mockito.verify(cartDao, Mockito.times(1)).save(cart);
        Assertions.assertEquals("index", result.getViewName());
        Assertions.assertEquals("Foods added to cart!", result.getModel().get("status"));
    }

    @Test
    void testDeleteProductFromCart() {
        Cart cart = new Cart();
        cart.setId(1);
        cart.setUserId(1);
        cart.setDate("2023-05-13");
        cart.setQuantity(2);
        cart.setFoodId(2);

        Optional<Cart> o = Optional.of(cart);
        Mockito.when(cartDao.findById(1)).thenReturn(o);

        ModelAndView result = cartController.deleteProductFromCart(1);

        Mockito.verify(cartDao, Mockito.times(1)).delete(cart);
        Assertions.assertEquals("index", result.getViewName());
        Assertions.assertEquals("Selected Food removed from Cart!", result.getModel().get("status"));
    }

    @Test
    void testDeleteProductFromCartNotFound() {
        Optional<Cart> o = Optional.empty();
        Mockito.when(cartDao.findById(1)).thenReturn(o);

        ModelAndView result = cartController.deleteProductFromCart(1);

        Assertions.assertEquals("index", result.getViewName());
    }

}