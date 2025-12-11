package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;

@Service
public class SafetyService {

    private final Driver driver;

    public SafetyService(Driver driver) {
        this.driver = driver;
    }

    @Transactional
    public void linkSGtoFSR(String sgId, String fsrId) {
        try (Session session = driver.session()) {
            session.run("""
                MATCH (s:SG {id:$sgId}), (f:FSR {id:$fsrId})
                MERGE (s)-[:HAS_FSR]->(f)
            """, 
            org.neo4j.driver.Values.parameters(
                "sgId", sgId,
                "fsrId", fsrId
            ));
        }
    }
}