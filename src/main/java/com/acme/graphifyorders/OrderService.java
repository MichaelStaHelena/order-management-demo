package com.acme.graphifyorders;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final Clock clock;
    public OrderService(OrderRepository repository, OrderMapper mapper, Clock clock) {
        this.repository = repository; this.mapper = mapper; this.clock = clock;
    }
    public OrderResponse create(CreateOrderRequest request) {
        OrderStatus status = request.status() == null ? OrderStatus.PENDING : request.status();
        Order order = new Order(request.customerName(), request.productName(), request.quantity(), status, Instant.now(clock));
        return mapper.toResponse(repository.save(order));
    }
    @Transactional(readOnly = true)
    public Order findEntity(long id) { return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)); }
    @Transactional(readOnly = true)
    public OrderResponse get(long id) { return mapper.toResponse(findEntity(id)); }
    @Transactional(readOnly = true)
    public List<OrderResponse> list() { return repository.findAllByOrderByIdAsc().stream().map(mapper::toResponse).toList(); }
    public OrderResponse update(long id, UpdateOrderRequest request) {
        Order order = findEntity(id);
        order.update(request.customerName(), request.productName(), request.quantity(), request.status());
        return mapper.toResponse(order);
    }
    public void delete(long id) { repository.delete(findEntity(id)); }
}
