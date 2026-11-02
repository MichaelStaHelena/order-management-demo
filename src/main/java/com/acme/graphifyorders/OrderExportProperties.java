package com.acme.graphifyorders;
import java.nio.file.Path;
import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "app.export")
public record OrderExportProperties(Path directory) { }
