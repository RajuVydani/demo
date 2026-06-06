package com.company.order.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InventorySearchRequest(
        String name,
        String category,
        String subcategory,
        String seller,
        String location,
        String brand,
        String model,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Integer minStock,
        LocalDate manufacturingDate,
        LocalDate expiryDate
) {}
