package com.company.order.exception;

import java.time.LocalDateTime;

/**
 * Standard API error response returned by the global exception handler.
 *
 * This is intentionally a simple POJO to allow clear JSON structure.
 */
public record ApiError(LocalDateTime timestamp, int status, String message, String path) {
}

