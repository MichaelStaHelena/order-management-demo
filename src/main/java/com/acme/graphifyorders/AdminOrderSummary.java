package com.acme.graphifyorders;
import java.time.Instant;
public record AdminOrderSummary(Long id, String customerName, String productName, int quantity, OrderStatus status, Instant createdAt) { }
