package com.company.order.mapper;

import com.company.order.dto.OrderResponse;
import com.company.order.entity.Order;
import com.company.order.dto.OrderCreateRequest;
import com.company.order.dto.OrderUpdateRequest;
import java.time.LocalDateTime;

/**
 * Manual mapper between Order entity and OrderResponse DTO.
 */
public final class OrderMapper {

    private OrderMapper() {
        // utility
    }

    /**
     * Map Order entity to OrderResponse DTO.
     *
     * @param order source entity
     * @return mapped DTO
     */
    public static OrderResponse toResponse(Order order) {
        if (order == null) return null;
        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

    public static Order toEntity(OrderCreateRequest req) {
        if (req == null) return null;
        return Order.builder()
                .customerName(req.customerName())
                .amount(req.amount())
                .status(req.status())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static void updateEntityFromRequest(Order order, OrderUpdateRequest req) {
        if (order == null || req == null) return;
        order.setCustomerName(req.customerName());
        order.setAmount(req.amount());
        order.setStatus(req.status());
        // createdAt must be preserved
    }
}

