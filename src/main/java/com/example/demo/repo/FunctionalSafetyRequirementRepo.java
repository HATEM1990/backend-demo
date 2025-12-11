package com.example.demo.repo;

import com.example.demo.model.FunctionalSafetyRequirement;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FunctionalSafetyRequirementRepo
        extends Neo4jRepository<FunctionalSafetyRequirement, String> {
}