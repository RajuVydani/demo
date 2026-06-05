//package com.company.order.repository;
//
//import com.company.order.entity.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//class OrderRepositoryIntegrationTest {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Test
//    void saveAndFindById() {
//        Order order = Order.builder()
//                .customerName("John Doe")
//                .amount(new BigDecimal("1500.50"))
//                .status("COMPLETED")
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        Order saved = orderRepository.save(order);
//
//        assertThat(saved.getOrderId()).isNotNull();
//
//        Order found = orderRepository.findById(saved.getOrderId()).orElseThrow();
//        assertThat(found.getCustomerName()).isEqualTo("John Doe");
//    }
//}
//
