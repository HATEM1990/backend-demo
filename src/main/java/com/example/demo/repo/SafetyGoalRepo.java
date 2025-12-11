package com.example.demo.repo;

import com.example.demo.model.SafetyGoal;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SafetyGoalRepo extends Neo4jRepository<SafetyGoal, String> {
}