package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("FSR")
public class FunctionalSafetyRequirement {

    @Id
    private String id;
    private String name;

    public FunctionalSafetyRequirement(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}