package com.company.order.dto;

import java.math.BigDecimal;

public record InventoryResponse(
        Long id,
        String name,
        String category,
        BigDecimal price,
        Integer stock
) {
}