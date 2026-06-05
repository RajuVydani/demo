package com.company.order.service;

import com.company.order.dto.OrderResponse;
import com.company.order.entity.Order;
import com.company.order.exception.OrderNotFoundException;
import com.company.order.mapper.OrderMapper;
import com.company.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order sampleOrder;

    @BeforeEach
    void setUp() {
        sampleOrder = Order.builder()
                .orderId(1001L)
                .customerName("John Doe")
                .amount(new BigDecimal("1500.50"))
                .status("COMPLETED")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void getOrderById_whenFound_returnsDto() {
        when(orderRepository.findById(1001L)).thenReturn(Optional.of(sampleOrder));

        OrderResponse resp = orderService.getOrderById(1001L);

        assertThat(resp).isNotNull();
        assertThat(resp.orderId()).isEqualTo(1001L);
        assertThat(resp.customerName()).isEqualTo("John Doe");
    }

    @Test
    void getOrderById_whenNotFound_throws() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.getOrderById(999L)).isInstanceOf(OrderNotFoundException.class);
    }

    @Test
    void createOrder_success() {
        when(orderRepository.save(org.mockito.ArgumentMatchers.any())).thenReturn(sampleOrder);

        var req = new com.company.order.dto.OrderCreateRequest("John Doe", new java.math.BigDecimal("1500.50"), "CREATED");
        var resp = orderService.createOrder(req);

        assertThat(resp).isNotNull();
        assertThat(resp.orderId()).isEqualTo(1001L);
        verify(orderRepository, times(1)).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void updateOrder_notFound_throws() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        var req = new com.company.order.dto.OrderUpdateRequest("New Name", new java.math.BigDecimal("10.00"), "UPDATED");
        assertThatThrownBy(() -> orderService.updateOrder(999L, req)).isInstanceOf(OrderNotFoundException.class);
    }

    @Test
    void deleteOrder_success() {
        when(orderRepository.findById(1001L)).thenReturn(Optional.of(sampleOrder));

        orderService.deleteOrder(1001L);

        verify(orderRepository, times(1)).delete(sampleOrder);
    }
}

