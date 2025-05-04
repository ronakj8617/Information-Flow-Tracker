package com.example.infoflowtracker.mongodb.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "access_logs")
public class AccessLog {

    @Id
    private String id;
    private String userId;
    private String role;
    private String dataResource;
    private String action;
    private SensitivityLevel sensitivityLevel;

    @CreatedDate
    private LocalDateTime timestamp;

    public AccessLog(String userId, String role, String dataResource, String action, SensitivityLevel sensitivityLevel) {
        this.userId = userId;
        this.role = role;
        this.dataResource = dataResource;
        this.action = action;
        this.sensitivityLevel = sensitivityLevel;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDataResource() { return dataResource; }
    public void setDataResource(String dataResource) { this.dataResource = dataResource; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public SensitivityLevel getSensitivityLevel() {
        return sensitivityLevel;
    }

    public void setSensitivityLevel(SensitivityLevel sensitivityLevel) {
        this.sensitivityLevel = sensitivityLevel;
    }
}
