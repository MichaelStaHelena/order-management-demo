package com.acme.graphifyorders;
import jakarta.validation.constraints.*;
public record UpdateOrderRequest(@NotBlank(message = "customerName is required") String customerName,
    @NotBlank(message = "productName is required") String productName,
    @Positive(message = "quantity must be positive") int quantity,
    @NotNull(message = "status is required") OrderStatus status) { }
