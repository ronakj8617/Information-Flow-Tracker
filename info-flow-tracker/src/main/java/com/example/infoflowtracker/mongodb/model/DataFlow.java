package com.example.infoflowtracker.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection = "data_flows")
public class DataFlow {

    private String sourceComponent;
    private String targetComponent;
    private String dataId;
    private SensitivityLevel sensitivity;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;

    public DataFlow(String sourceComponent, String targetComponent, String dataId, SensitivityLevel sensitivity) {
        this.sourceComponent = sourceComponent;
        this.targetComponent = targetComponent;
        this.dataId = dataId;
        this.sensitivity = sensitivity;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public SensitivityLevel getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(SensitivityLevel sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getTargetComponent() {
        return targetComponent;
    }

    public void setTargetComponent(String targetComponent) {
        this.targetComponent = targetComponent;
    }

    public String getSourceComponent() {
        return sourceComponent;
    }

    public void setSourceComponent(String sourceComponent) {
        this.sourceComponent = sourceComponent;
    }
}
