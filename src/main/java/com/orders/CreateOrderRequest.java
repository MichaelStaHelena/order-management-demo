package com.orders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateOrderRequest(
    @NotBlank(message = "customerName is required") String customerName,
    @NotBlank(message = "productName is required") String productName,
    @Positive(message = "quantity must be positive") int quantity,
    OrderStatus status) {}
