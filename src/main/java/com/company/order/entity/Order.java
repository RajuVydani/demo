package com.company.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * JPA entity that maps to ORDERS table.
 */
@Entity
@Table(name = "orders", indexes = {
        @Index(name = "idx_orders_status", columnList = "status"),
        @Index(name = "idx_orders_customer_name", columnList = "customer_name")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, updatable = false)
    private Long orderId;

    @Column(name = "customer_name", nullable = false, length = 200)
    private String customerName;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

}

