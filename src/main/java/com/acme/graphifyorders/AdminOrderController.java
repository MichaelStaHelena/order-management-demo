package com.acme.graphifyorders;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final OrderExportService exportService;
    public AdminOrderController(OrderService orderService, OrderMapper mapper, OrderExportService exportService) {
        this.orderService = orderService; this.mapper = mapper; this.exportService = exportService;
    }
    @GetMapping("/{id}/summary") public AdminOrderSummary summary(@PathVariable long id) { return mapper.toAdminSummary(orderService.findEntity(id)); }
    @GetMapping(value = "/export", produces = "text/csv") public String export() { return exportService.exportCsv(); }
}
