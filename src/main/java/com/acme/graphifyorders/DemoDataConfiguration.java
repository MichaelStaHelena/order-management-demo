package com.acme.graphifyorders;

import java.time.Clock;
import java.time.Instant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration @Profile("demo")
public class DemoDataConfiguration {
    @Bean CommandLineRunner demoOrders(OrderRepository repository, Clock clock) {
        return args -> { if (repository.count() == 0) {
            repository.save(new Order("Ada Lovelace", "Mechanical keyboard", 1, OrderStatus.PROCESSING, Instant.now(clock)));
            repository.save(new Order("Grace Hopper", "USB-C dock", 2, OrderStatus.PENDING, Instant.now(clock)));
        }};
    }
}
