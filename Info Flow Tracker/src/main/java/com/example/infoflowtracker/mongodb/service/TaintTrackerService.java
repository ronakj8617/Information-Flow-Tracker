package com.example.infoflowtracker.mongodb.service;

import com.example.infoflowtracker.mongodb.model.SensitivityLevel;
import org.springframework.stereotype.Service;

@Service
public class TaintTrackerService {

    // Check if a taint violation occurred: Sensitive data flows to a less sensitive resource.
    public boolean isViolation(SensitivityLevel source, SensitivityLevel target) {
        return source.ordinal() > target.ordinal(); // Conf → Public = Violation
    }

    // Method to log data flow (for dynamic tracking)
    public void trackDataFlow(String source, String target, SensitivityLevel level) {
        if (isViolation(level, getTargetSensitivity(target))) {
            throw new SecurityException("Taint policy violated!"); // Enforce policy
        }
    }

    private SensitivityLevel getTargetSensitivity(String target) {
        // Mock logic: Just an example, this can be more sophisticated
        if (target.equals("public_resource")) return SensitivityLevel.PUBLIC;
        return SensitivityLevel.RESTRICTED;
    }


}
