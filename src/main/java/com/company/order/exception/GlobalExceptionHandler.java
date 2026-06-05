package com.company.order.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Global exception handler that converts exceptions into standardized API errors.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(OrderNotFoundException ex, HttpServletRequest request) {
        log.warn("Order not found: {}", ex.getMessage());
        ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Order not found", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleValidation(Exception ex, HttpServletRequest request) {
        log.warn("Validation error: {}", ex.getMessage());
        ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation failed", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.warn("Data integrity violation: {}", ex.getMessage());
        ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Data integrity violation", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
        log.error("Unhandled error: {}", ex.getMessage(), ex);
        ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

