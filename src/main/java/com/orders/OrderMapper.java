package com.orders;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
  public OrderResponse toResponse(Order order) {
    return new OrderResponse(
        order.getId(),
        order.getCustomerName(),
        order.getProductName(),
        order.getQuantity(),
        order.getStatus(),
        order.getCreatedAt());
  }

  public AdminOrderSummary toAdminSummary(Order order) {
    return new AdminOrderSummary(
        order.getId(),
        order.getCustomerName(),
        order.getProductName(),
        order.getQuantity(),
        order.getStatus(),
        order.getCreatedAt());
  }
}
