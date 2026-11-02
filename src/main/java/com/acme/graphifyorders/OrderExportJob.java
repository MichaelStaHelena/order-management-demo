package com.acme.graphifyorders;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.export.scheduling.enabled", havingValue = "true")
public class OrderExportJob {
    private static final Logger log = LoggerFactory.getLogger(OrderExportJob.class);
    private final OrderExportService exportService;
    public OrderExportJob(OrderExportService exportService) { this.exportService = exportService; }
    @Scheduled(fixedDelayString = "${app.export.scheduling.fixed-delay:60000}")
    public void exportOrders() {
        try { log.info("Wrote order export to {}", exportService.writeExport()); }
        catch (IOException exception) { log.error("Scheduled order export failed", exception); }
    }
}
