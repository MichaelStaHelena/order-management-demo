package com.acme.graphifyorders;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) { this.orderService = orderService; }
    @PostMapping public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.create(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.id())).body(response);
    }
    @GetMapping("/{id}") public OrderResponse get(@PathVariable long id) { return orderService.get(id); }
    @GetMapping public List<OrderResponse> list() { return orderService.list(); }
    @PutMapping("/{id}") public OrderResponse update(@PathVariable long id, @Valid @RequestBody UpdateOrderRequest request) { return orderService.update(id, request); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable long id) { orderService.delete(id); return ResponseEntity.noContent().build(); }
}
