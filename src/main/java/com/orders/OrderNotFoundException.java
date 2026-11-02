package com.orders;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(long id) {
    super("Order " + id + " was not found");
  }
}
