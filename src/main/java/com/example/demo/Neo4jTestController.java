package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;

@RestController
public class Neo4jTestController {

    @Autowired
    private Driver driver;

    @GetMapping("/test-neo4j")
    public String testNeo4j() {
        try (Session session = driver.session()) {
            Result result = session.run("RETURN 'Neo4j OK' AS msg");
            return result.single().get("msg").asString();
        } catch (Exception e) {
            return "Neo4j ERROR: " + e.getMessage();
        }
    }
}