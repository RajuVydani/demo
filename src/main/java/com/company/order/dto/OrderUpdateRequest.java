package com.company.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * DTO for updating an order. Fields that can be updated:
 * customerName, amount, status
 */
public record OrderUpdateRequest(
        @NotBlank(message = "customerName must not be blank") String customerName,
        @NotNull(message = "amount must be provided") @Positive(message = "amount must be greater than 0") BigDecimal amount,
        @NotNull(message = "status must be provided") String status
) {
}

