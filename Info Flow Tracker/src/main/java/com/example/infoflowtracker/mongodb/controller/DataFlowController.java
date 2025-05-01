package com.example.infoflowtracker.mongodb.controller;

import com.example.infoflowtracker.mongodb.model.DataFlow;
import com.example.infoflowtracker.mongodb.model.SensitivityLevel;
import com.example.infoflowtracker.mongodb.repository.DataFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flows")
public class DataFlowController {

    @Autowired
    private DataFlowRepository dataFlowRepository;

    // Create a data flow (track)
    @PostMapping
    public ResponseEntity<DataFlow> createFlow(@RequestBody DataFlow dataFlow) {
        System.out.println("Received: " + dataFlow);
        return ResponseEntity.ok(dataFlowRepository.save(dataFlow));
    }



    // List all data flows
    @GetMapping
    public ResponseEntity<List<DataFlow>> getAllFlows() {
        return ResponseEntity.ok(dataFlowRepository.findAll());
    }

    // Return flows in D3.js-friendly format
    @GetMapping("/graph")
    public ResponseEntity<List<Map<String, Object>>> getGraphData() {
        List<DataFlow> flows = dataFlowRepository.findAll();
        List<Map<String, Object>> graphData = new ArrayList<>();

        for (DataFlow flow : flows) {
            Map<String, Object> edge = new HashMap<>();
            edge.put("source", flow.getSourceComponent());
            edge.put("target", flow.getTargetComponent());
            edge.put("sourceTaint", flow.getSensitivity().name()); // Add this
            edge.put("targetTaint", getTargetSensitivity(flow.getTargetComponent()).name()); // Add this
            graphData.add(edge);
        }

        return ResponseEntity.ok(graphData);
    }
    private SensitivityLevel getTargetSensitivity(String component) {
        if (component.equalsIgnoreCase("public_resource") || component.contains("Public")) {
            return SensitivityLevel.PUBLIC;
        }
        // You can add more sophisticated logic here
        return SensitivityLevel.RESTRICTED;
    }

    @GetMapping("/api/flows/graph")
    public List<Map<String, Object>> getDataFlows() {
        List<DataFlow> flows = dataFlowRepository.findAll();

        // Filter out invalid flows with null source or target
        List<DataFlow> validFlows = flows.stream()
                .filter(flow -> flow.getDataId() != null && flow.getSourceComponent() != null && flow.getSensitivity() != null && flow.getTimestamp() != null && flow.getTargetComponent() != null)
                .collect(Collectors.toList());

        List<Map<String, Object>> graphData = new ArrayList<>();
        for (DataFlow flow : validFlows) {
            Map<String, Object> flowMap = new HashMap<>();
            flowMap.put("source", flow.getSourceComponent());
            flowMap.put("target", flow.getTargetComponent());
            flowMap.put("value", 1); // Mock value, replace with actual if needed
            graphData.add(flowMap);
        }

        return (List<Map<String, Object>>) ResponseEntity.ok(graphData);
    }

}
