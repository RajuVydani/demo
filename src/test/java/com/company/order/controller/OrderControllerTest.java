//package com.company.order.controller;
//
//import com.company.order.dto.OrderResponse;
//import com.company.order.service.OrderService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(OrderController.class)
//class OrderControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private OrderService orderService;
//
//	@Test
//	void getOrder_returnsOk() throws Exception {
//		OrderResponse resp = new OrderResponse(1001L, "John Doe", new BigDecimal("1500.50"), "COMPLETED", LocalDateTime.of(2026, 6, 5, 10, 15, 30));
//
//		when(orderService.getOrderById(1001L)).thenReturn(resp);
//
//		mockMvc.perform(get("/api/v1/orders/1001").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.orderId").value(1001))
//				.andExpect(jsonPath("$.customerName").value("John Doe"))
//				.andExpect(jsonPath("$.amount").value(1500.50))
//				.andExpect(jsonPath("$.status").value("COMPLETED"))
//				.andExpect(jsonPath("$.createdAt").exists());
//	}
//
//	@Test
//	void createOrder_returnsCreated() throws Exception {
//		OrderResponse resp = new OrderResponse(1002L, "Jane", new BigDecimal("100.00"), "CREATED", LocalDateTime.now());
//		when(orderService.createOrder(org.mockito.ArgumentMatchers.any())).thenReturn(resp);
//
//		String body = "{\"customerName\":\"Jane\",\"amount\":100.00,\"status\":\"CREATED\"}";
//
//		mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/orders")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(body))
//				.andExpect(status().isCreated())
//				.andExpect(jsonPath("$.orderId").value(1002));
//	}
//
//	@Test
//	void deleteOrder_returnsNoContent() throws Exception {
//		doNothing().when(orderService).deleteOrder(1001L);
//
//		mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/v1/orders/1001"))
//				.andExpect(status().isNoContent());
//	}
//}
//
