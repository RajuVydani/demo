package com.company.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO returned by the API. Implemented as a Java 17 record.
 *
 * @param orderId      order identifier
 * @param customerName customer name
 * @param amount       order amount
 * @param status       order status
 * @param createdAt    timestamp when the order was created
 */
public record OrderResponse(Long orderId,
                            String customerName,
                            BigDecimal amount,
                            String status,
                            LocalDateTime createdAt) {
}

