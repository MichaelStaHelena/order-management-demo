# Graph Report - order-management-demo  (2026-11-02)

## Corpus Check
- 25 files · ~3,322 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 139 nodes · 238 edges · 12 communities (9 shown, 3 thin omitted)
- Extraction: 89% EXTRACTED · 11% INFERRED · 0% AMBIGUOUS · INFERRED: 27 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `7140943f`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- OrderExportService
- OrderService
- Order
- .error
- OrderRepository
- OrderApiIntegrationTest
- OrderManagementApplication
- mvnw
- verify.sh
- demo-requests.sh
- com.acme:order-management-demo
- Order Management Demo — Graphify map

## God Nodes (most connected - your core abstractions)
1. `Order` - 18 edges
2. `OrderService` - 18 edges
3. `OrderExportService` - 12 edges
4. `OrderStatus` - 12 edges
5. `OrderResponse` - 11 edges
6. `OrderController` - 10 edges
7. `AdminOrderController` - 9 edges
8. `OrderMapper` - 8 edges
9. `OrderExportJob` - 7 edges
10. `OrderManagementApplication` - 7 edges

## Surprising Connections (you probably didn't know these)
- `AdminOrderController` --references--> `OrderService`  [EXTRACTED]
  src/main/java/com/orders/AdminOrderController.java → src/main/java/com/orders/OrderService.java
- `CreateOrderRequest` --references--> `OrderStatus`  [EXTRACTED]
  src/main/java/com/orders/CreateOrderRequest.java → src/main/java/com/orders/OrderStatus.java
- `OrderRepository` --references--> `Order`  [EXTRACTED]
  src/main/java/com/orders/OrderRepository.java → src/main/java/com/orders/Order.java
- `OrderManagementApplication` --references--> `OrderExportProperties`  [EXTRACTED]
  src/main/java/com/orders/OrderManagementApplication.java → src/main/java/com/orders/OrderExportProperties.java
- `OrderExportService` --references--> `OrderService`  [EXTRACTED]
  src/main/java/com/orders/OrderExportService.java → src/main/java/com/orders/OrderService.java

## Import Cycles
- None detected.

## Communities (12 total, 3 thin omitted)

### Community 0 - "OrderExportService"
Cohesion: 0.11
Nodes (14): org.slf4j.Logger, org.springframework.boot.autoconfigure.condition.ConditionalOnProperty, org.springframework.boot.context.properties.ConfigurationProperties, org.springframework.scheduling.annotation.Scheduled, org.springframework.stereotype.Component, org.springframework.stereotype.Service, AdminOrderController, GetMapping (+6 more)

### Community 1 - "OrderService"
Cohesion: 0.12
Nodes (13): DeleteMapping, org.springframework.http.ResponseEntity, org.springframework.transaction.annotation.Transactional, PostMapping, PutMapping, CreateOrderRequest, GetMapping, RequestMapping (+5 more)

### Community 2 - "Order"
Cohesion: 0.17
Nodes (8): Entity, AdminOrderSummary, Order, OrderStatus, PENDING, PROCESSING, SHIPPED, Table

### Community 3 - ".error"
Cohesion: 0.21
Nodes (8): ExceptionHandler, HttpStatus, org.springframework.web.bind.MethodArgumentNotValidException, RestControllerAdvice, ApiError, GlobalExceptionHandler, ResponseEntity, OrderNotFoundException

### Community 4 - "OrderRepository"
Cohesion: 0.22
Nodes (7): Configuration, org.springframework.boot.CommandLineRunner, org.springframework.data.jpa.repository.JpaRepository, Profile, DemoDataConfiguration, Bean, OrderRepository

### Community 5 - "OrderApiIntegrationTest"
Cohesion: 0.43
Nodes (5): org.junit.jupiter.api.Test, org.springframework.boot.test.context.SpringBootTest, org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc, org.springframework.test.web.servlet.MockMvc, OrderApiIntegrationTest

### Community 6 - "OrderManagementApplication"
Cohesion: 0.39
Nodes (5): org.springframework.boot.autoconfigure.SpringBootApplication, org.springframework.boot.context.properties.EnableConfigurationProperties, org.springframework.context.annotation.Bean, org.springframework.scheduling.annotation.EnableScheduling, OrderManagementApplication

### Community 7 - "mvnw"
Cohesion: 0.60
Nodes (5): mvnw script, concat_lines(), find_maven_basedir(), log(), trim()

### Community 11 - "Order Management Demo — Graphify map"
Cohesion: 0.40
Nodes (4): Explore the graph, Graphify command reference, Order Management Demo — Graphify map, Typical Codex workflow

## Knowledge Gaps
- **9 isolated node(s):** `com.acme:order-management-demo`, `demo-requests.sh script`, `verify.sh script`, `PENDING`, `PROCESSING` (+4 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 37 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **3 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `OrderService` connect `OrderService` to `OrderExportService`, `OrderRepository`?**
  _High betweenness centrality (0.215) - this node is a cross-community bridge._
- **Why does `OrderExportService` connect `OrderExportService` to `OrderService`?**
  _High betweenness centrality (0.126) - this node is a cross-community bridge._
- **Why does `OrderRepository` connect `OrderRepository` to `OrderExportService`, `OrderService`, `Order`?**
  _High betweenness centrality (0.097) - this node is a cross-community bridge._
- **What connects `com.acme:order-management-demo`, `demo-requests.sh script`, `verify.sh script` to the rest of the system?**
  _9 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `OrderExportService` be split into smaller, more focused modules?**
  _Cohesion score 0.11083743842364532 - nodes in this community are weakly interconnected._
- **Should `OrderService` be split into smaller, more focused modules?**
  _Cohesion score 0.12413793103448276 - nodes in this community are weakly interconnected._