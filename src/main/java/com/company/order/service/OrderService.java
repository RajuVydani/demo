package com.company.order.service;

import com.company.order.dto.OrderCreateRequest;
import com.company.order.dto.OrderResponse;
import com.company.order.dto.OrderUpdateRequest;
import com.company.order.entity.Order;
import com.company.order.exception.OrderNotFoundException;
import com.company.order.mapper.OrderMapper;
import com.company.order.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service responsible for order business logic.
 */
@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Create a new order from a create request.
     */
    @Transactional
    public OrderResponse createOrder(OrderCreateRequest req) {
        log.info("Service start - createOrder: {}", req);
        var entity = com.company.order.mapper.OrderMapper.toEntity(req);
        var saved = orderRepository.save(entity);
        log.info("Order created - id={}", saved.getOrderId());
        return com.company.order.mapper.OrderMapper.toResponse(saved);
    }

    /**
     * Update an existing order.
     */
    @Transactional
    public OrderResponse updateOrder(Long orderId, OrderUpdateRequest req) {
        log.info("Service start - updateOrder: {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        com.company.order.mapper.OrderMapper.updateEntityFromRequest(order, req);
        Order saved = orderRepository.save(order);
        log.info("Order updated - id={}", orderId);
        return com.company.order.mapper.OrderMapper.toResponse(saved);
    }

    /**
     * Delete an order by id.
     */
    @Transactional
    public void deleteOrder(Long orderId) {
        log.info("Service start - deleteOrder: {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        orderRepository.delete(order);
        log.info("Order deleted - id={}", orderId);
    }

    /**
     * List orders with pagination.
     */
    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<OrderResponse> listOrders(org.springframework.data.domain.Pageable pageable) {
        log.info("Service start - listOrders: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return orderRepository.findAll(pageable).map(com.company.order.mapper.OrderMapper::toResponse);
    }

    /**
     * Fetches an order by id and maps it to a response DTO.
     *
     * @param orderId order id
     * @return mapped response DTO
     * @throws OrderNotFoundException when order does not exist
     */
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long orderId) {
        log.info("Service start - getOrderById: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        log.info("Order found - id={}", orderId);
        return OrderMapper.toResponse(order);
    }
}


