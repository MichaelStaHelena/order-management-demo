package com.acme.graphifyorders;
public class OrderNotFoundException extends RuntimeException { public OrderNotFoundException(long id) { super("Order " + id + " was not found"); } }
