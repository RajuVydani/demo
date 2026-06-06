package com.company.order.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private String subcategory;

    private LocalDate manufacturingDate;

    private LocalDate expiryDate;

    @Column(columnDefinition = "TEXT")
    private String specification;

    private BigDecimal price;

    private Integer stock;

    private String model;

    private String seller;

    private String location;

    private String brand;

    private String sku;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

