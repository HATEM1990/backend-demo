package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;

@RestController
public class Neo4jCrudController {

    @Autowired
    private Driver driver;

    // WRITE: Create a node in AuraDB
    @GetMapping("/neo4j-write")
    public String writeData() {
        try (Session session = driver.session()) {
            session.run("CREATE (n:TestNode {name:'FuSa Node', createdAt: datetime()})");
            return "Write OK";
        } catch (Exception e) {
            return "Neo4j WRITE ERROR: " + e.getMessage();
        }
    }

    // READ: Retrieve nodes from AuraDB
    @GetMapping("/neo4j-read")
    public String readData() {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (n:TestNode) RETURN n.name AS name LIMIT 5");
            if (!result.hasNext()) {
                return "No TestNode found";
            }
            StringBuilder response = new StringBuilder("Nodes:\n");
            while (result.hasNext()) {
                var record = result.next();
                response.append("- ").append(record.get("name").asString()).append("\n");
            }
            return response.toString();
        } catch (Exception e) {
            return "Neo4j READ ERROR: " + e.getMessage();
        }
    }
}