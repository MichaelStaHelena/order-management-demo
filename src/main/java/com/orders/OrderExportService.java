package com.orders;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class OrderExportService {
  private final OrderService orderService;
  private final OrderExportProperties properties;
  private final Clock clock;

  public OrderExportService(
      OrderService orderService, OrderExportProperties properties, Clock clock) {
    this.orderService = orderService;
    this.properties = properties;
    this.clock = clock;
  }

  public String exportCsv() {
    StringBuilder csv =
        new StringBuilder("id,customerName,productName,quantity,status,createdAt\n");
    orderService
        .list()
        .forEach(
            order ->
                csv.append(order.id())
                    .append(',')
                    .append(escape(order.customerName()))
                    .append(',')
                    .append(escape(order.productName()))
                    .append(',')
                    .append(order.quantity())
                    .append(',')
                    .append(order.status())
                    .append(',')
                    .append(order.createdAt())
                    .append('\n'));
    return csv.toString();
  }

  public Path writeExport() throws IOException {
    Files.createDirectories(properties.directory());
    Path target =
        properties.directory().resolve("orders-" + Instant.now(clock).toEpochMilli() + ".csv");
    Files.writeString(target, exportCsv(), StandardCharsets.UTF_8);
    return target;
  }

  private String escape(String value) {
    return '"' + value.replace("\"", "\"\"") + '"';
  }
}
