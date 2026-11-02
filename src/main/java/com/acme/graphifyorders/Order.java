package com.acme.graphifyorders;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String customerName;
    private String productName;
    private int quantity;
    @Enumerated(EnumType.STRING) private OrderStatus status;
    private Instant createdAt;
    protected Order() { }
    public Order(String customerName, String productName, int quantity, OrderStatus status, Instant createdAt) {
        this.customerName = customerName; this.productName = productName; this.quantity = quantity;
        this.status = status; this.createdAt = createdAt;
    }
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public OrderStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public void update(String customerName, String productName, int quantity, OrderStatus status) {
        this.customerName = customerName; this.productName = productName; this.quantity = quantity; this.status = status;
    }
}
