package com.example.infoflowtracker.mongodb.repository;

import com.example.infoflowtracker.mongodb.model.DataFlow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataFlowRepository extends MongoRepository<DataFlow, String> {
}
