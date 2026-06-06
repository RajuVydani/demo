package com.company.order.controller;

import com.company.order.dto.InventoryResponse;
import com.company.order.dto.InventorySearchRequest;
import com.company.order.exception.BadRequestException;
import com.company.order.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
@Validated
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> search(

            @RequestParam(required = false)
            String name,

            @RequestParam(required = false)
            String category,

            @RequestParam(required = false)
            String subcategory,

            @RequestParam(required = false)
            String seller,

            @RequestParam(required = false)
            String location,

            @RequestParam(required = false)
            String brand,

            @RequestParam(required = false)
            String model,

            @RequestParam(required = false)
            @DecimalMin(value = "0.0", inclusive = true, message = "minPrice must be >= 0")
            BigDecimal minPrice,

            @RequestParam(required = false)
            @DecimalMin(value = "0.0", inclusive = true, message = "maxPrice must be >= 0")
            BigDecimal maxPrice,

            @RequestParam(required = false)
            @Min(value = 0, message = "minStock must be >= 0")
            Integer minStock,

            @RequestParam(required = false)
            @PastOrPresent(message = "manufacturingDate must be in the past or present")
            LocalDate manufacturingDate,

            @RequestParam(required = false)
            @Future(message = "expiryDate must be in the future")
            LocalDate expiryDate) {

        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new BadRequestException("minPrice must be less than or equal to maxPrice");
        }

        if (manufacturingDate != null && expiryDate != null && manufacturingDate.isAfter(expiryDate)) {
            throw new BadRequestException("manufacturingDate must be before or equal to expiryDate");
        }

        InventorySearchRequest request =
                new InventorySearchRequest(
                        name,
                        category,
                        subcategory,
                        seller,
                        location,
                        brand,
                        model,
                        minPrice,
                        maxPrice,
                        minStock,
                        manufacturingDate,
                        expiryDate);

        return ResponseEntity.ok(
                inventoryService.search(request));
    }
}
