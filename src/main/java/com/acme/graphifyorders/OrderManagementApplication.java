package com.acme.graphifyorders;

import java.time.Clock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(OrderExportProperties.class)
public class OrderManagementApplication {
    public static void main(String[] args) { SpringApplication.run(OrderManagementApplication.class, args); }
    @Bean Clock clock() { return Clock.systemUTC(); }
}
