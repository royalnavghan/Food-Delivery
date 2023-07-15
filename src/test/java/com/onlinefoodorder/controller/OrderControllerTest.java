package com.onlinefoodorder.controller;

import com.onlinefoodorder.dao.CartDao;
import com.onlinefoodorder.dao.OrderDao;
import com.onlinefoodorder.model.Cart;
import com.onlinefoodorder.model.Orders;
import com.onlinefoodorder.model.User;
import com.onlinefoodorder.utility.Constants;
import com.onlinefoodorder.utility.Helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderControllerTest {

	@Mock
	private CartDao cartDao;

	@Mock
	private OrderDao orderDao;

	@InjectMocks
	private OrderController orderController;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testOrderFoods() {
		HttpSession session = new MockHttpSession();
		User user = new User();
		user.setId(1);
		session.setAttribute("active-user", user);

		List<Cart> carts = new ArrayList<>();
		carts.add(new Cart());
		carts.add(new Cart());

		when(cartDao.findByUserId(user.getId())).thenReturn(carts);
		when(orderDao.save(any(Orders.class))).thenReturn(new Orders());

		ModelAndView mv = orderController.orderfoods(session);

		verify(cartDao, times(2)).delete(any(Cart.class));
		verify(orderDao, times(2)).save(any(Orders.class));

		assertEquals("index", mv.getViewName());
		assertEquals("Order placed Successfully", mv.getModel().get("statusWithoutOrderId"));
	}

	@Test
	void testGoToMyOrder() {
		HttpSession session = new MockHttpSession();
		User user = new User();
		user.setId(1);
		session.setAttribute("active-user", user);

		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders());
		orders.add(new Orders());

		when(orderDao.findByUserId(user.getId())).thenReturn(orders);

		ModelAndView mv = orderController.goToMyOrder(session);

		verify(orderDao, times(1)).findByUserId(user.getId());

		assertEquals("myorder", mv.getViewName());
		assertEquals(orders, mv.getModel().get("orders"));
	}

//	@Test
//	void testSearchByOrderId() {
//		ModelAndView mv = orderController.searchByOrderId("jYIOmZFeXL");
//
//		Orders order = new Orders();
//		order.setOrderDate("2023-05-06");
//		order.setOrderId("jYIOmZFeXL");
//		order.setUserId(5);
//		order.setQuantity(1);
//		order.setFoodId(4);
//		order.setDeliveryStatus(Constants.DeliveryStatus.DELIVERED.value());
//		order.setDeliveryDate("1");
//		
//		List<Orders> orders = new ArrayList<>();
//		orders.add(order);
//
//		assertEquals("myorder", mv.getViewName());
//		assertEquals(orders, mv.getModel().get("orders"));
//	}

	@Test
	void testSearchByOrderDate() {
		HttpSession session = new MockHttpSession();
		User user = new User();
		user.setId(1);
		session.setAttribute("active-user", user);

		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders());
		orders.add(new Orders());

		when(orderDao.findByOrderDateAndUserId("2023-05-10", user.getId())).thenReturn(orders);

		ModelAndView mv = orderController.searchByOrderDate("2023-05-10", session);

		verify(orderDao, times(1)).findByOrderDateAndUserId("2023-05-10", user.getId());

		assertEquals("myorder", mv.getViewName());
		assertEquals(orders, mv.getModel().get("orders"));
	}

	@Test
	void testSearchByOrderDateWithAmount() {
		ModelAndView mv = orderController.searchByOrderDate("100.00");

		assertEquals("checkout", mv.getViewName());
		assertEquals("100.00", mv.getModel().get("amount"));
	}

	@Test
	void testUpdateDeliveryDate() {
		ModelAndView mv = orderController.addDeliveryStatus("ABC123", "Delivered", "2023-05-15");

		assertEquals("index", mv.getViewName());
		assertEquals("Order Delivery Status Updated.", mv.getModel().get("status"));
	}

}
