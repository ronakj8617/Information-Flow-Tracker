package com.example.infoflowtracker.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "data_flows")
public class DataFlow {
    private String sourceComponent;
    private String targetComponent;
    private String dataId;
    private SensitivityLevel sensitivity;
    private LocalDateTime timestamp;

    public DataFlow(String sourceComponent, String targetComponent, String dataId, SensitivityLevel sensitivity) {
        this.sourceComponent = sourceComponent;
        this.targetComponent = targetComponent;
        this.dataId = dataId;
        this.sensitivity = sensitivity;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
}
