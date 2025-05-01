package com.example.infoflowtracker.mongodb.controller;

import com.example.infoflowtracker.mongodb.model.DataFlow;
import com.example.infoflowtracker.mongodb.repository.DataFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flows")
public class DataFlowController {

    @Autowired
    private DataFlowRepository repository;

    @PostMapping("/track")
    public DataFlow trackFlow(@RequestBody DataFlow dataFlow) {
        return repository.save(dataFlow);
    }

    @GetMapping("/all")
    public List<DataFlow> getAllFlows() {
        return repository.findAll();
    }
}
