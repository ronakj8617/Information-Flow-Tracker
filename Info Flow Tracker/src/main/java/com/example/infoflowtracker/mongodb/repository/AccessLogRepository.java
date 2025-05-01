package com.example.infoflowtracker.mongodb.repository;

import com.example.infoflowtracker.mongodb.model.AccessLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AccessLogRepository extends MongoRepository<AccessLog, String> {
    List<AccessLog> findByUserId(String userId);
    List<AccessLog> findByAction(String action);
    List<AccessLog> findByUserIdAndAction(String userId, String action);
}
