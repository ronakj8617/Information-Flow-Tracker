package com.example.infoflowtracker.mongodb.controller;

import com.example.infoflowtracker.mongodb.model.AccessLog;
import com.example.infoflowtracker.mongodb.repository.AccessLogRepository;
import com.example.infoflowtracker.mongodb.service.TaintTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class AccessLogController {

    @Autowired
    private AccessLogRepository repository;

    @Autowired
    private TaintTrackerService taintTrackerService;

    @PostMapping("/access")
    public ResponseEntity<?> logAccess(@RequestBody AccessLog accessLog) {
        // Dynamically track data flow with taint checks
        taintTrackerService.trackDataFlow(accessLog.getDataResource(), "target_resource", accessLog.getSensitivityLevel());

        return ResponseEntity.ok(repository.save(accessLog));
    }

//    // Only one POST endpoint with authorization logic
//    @PostMapping("/access")
//    public ResponseEntity<?> logAccess(@RequestBody AccessLog accessLog) {
//        if ("confidential".equalsIgnoreCase(accessLog.getDataResource())
//                && !"admin".equalsIgnoreCase(accessLog.getRole())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                    .body("Unauthorized access to confidential resource");
//        }
//
//        return ResponseEntity.ok(repository.save(accessLog));
//    }

    @GetMapping("/access/search")
    public List<AccessLog> searchLogs(@RequestParam(required = false) String userId,
                                      @RequestParam(required = false) String action) {
        if (userId != null && action != null)
            return repository.findByUserIdAndAction(userId, action);
        else if (userId != null)
            return repository.findByUserId(userId);
        else if (action != null)
            return repository.findByAction(action);
        else
            return repository.findAll();
    }

    @GetMapping("/access")
    public List<AccessLog> getAllLogs() {
        return repository.findAll();
    }
}
