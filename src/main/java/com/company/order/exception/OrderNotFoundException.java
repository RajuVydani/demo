package com.company.order.exception;

/**
 * Exception thrown when an order cannot be found.
 */
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super("Order not found: " + orderId);
    }
}

