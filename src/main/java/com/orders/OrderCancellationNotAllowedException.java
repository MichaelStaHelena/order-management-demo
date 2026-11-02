package com.orders;

public class OrderCancellationNotAllowedException extends RuntimeException {
  public OrderCancellationNotAllowedException(long id, OrderStatus status) {
    super("Order " + id + " cannot be cancelled because it is " + status);
  }
}
