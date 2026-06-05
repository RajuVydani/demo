package com.company.order.controller;

import com.company.order.dto.OrderCreateRequest;
import com.company.order.dto.OrderResponse;
import com.company.order.dto.OrderUpdateRequest;
import com.company.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing order endpoints.
 */
@RestController
@RequestMapping("/api/v1/orders")
@Validated
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * GET /api/v1/orders/{orderId} - fetch order details.
     *
     * @param orderId order id, must be positive
     * @return OrderResponse wrapped in ResponseEntity
     */
    @Operation(summary = "Get order by id", responses = {
            @ApiResponse(responseCode = "200", description = "Order found", content = @Content(schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("orderId") @Positive(message = "orderId must be positive") Long orderId) {
        log.info("API request received - GET /api/v1/orders/{}", orderId);
        OrderResponse response = orderService.getOrderById(orderId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create order", responses = {
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content(schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    @org.springframework.web.bind.annotation.PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @org.springframework.web.bind.annotation.RequestBody OrderCreateRequest req) {
        log.info("API request received - POST /api/v1/orders - {}", req);
        OrderResponse created = orderService.createOrder(req);
        java.net.URI location = org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.orderId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Update order", responses = {
            @ApiResponse(responseCode = "200", description = "Order updated", content = @Content(schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @org.springframework.web.bind.annotation.PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable("orderId") @Positive(message = "orderId must be positive") Long orderId,
                                                     @Valid @org.springframework.web.bind.annotation.RequestBody OrderUpdateRequest req) {
        log.info("API request received - PUT /api/v1/orders/{} - {}", orderId, req);
        OrderResponse updated = orderService.updateOrder(orderId, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete order", responses = {
            @ApiResponse(responseCode = "204", description = "Order deleted"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @org.springframework.web.bind.annotation.DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") @Positive(message = "orderId must be positive") Long orderId) {
        log.info("API request received - DELETE /api/v1/orders/{}", orderId);
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List orders", responses = {
            @ApiResponse(responseCode = "200", description = "Paged orders list")
    })
    @GetMapping
    public ResponseEntity<Page<OrderResponse>> listOrders(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        log.info("API request received - GET /api/v1/orders - page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<OrderResponse> page = orderService.listOrders(pageable);
        return ResponseEntity.ok(page);
    }
}

