package com.example.demo.controller;

import com.example.demo.model.SafetyGoal;
import com.example.demo.model.FunctionalSafetyRequirement;
import com.example.demo.service.SafetyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")   // <---- CRITICAL
@CrossOrigin
public class SafetyController {

    private final SafetyService safetyService;

    public SafetyController(SafetyService safetyService) {
        this.safetyService = safetyService;
    }

    // Create SG
    @GetMapping("/sg")
    public SafetyGoal createSG(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String sgId) {

        if (name != null) {
            return safetyService.createSafetyGoal(name);
        }
        if (sgId != null) {
            return safetyService.getSafetyGoalWithFsrs(sgId);
        }
        return null;
    }

    // List all SGs
    @GetMapping("/sg/all")
    public List<SafetyGoal> getAllSGs() {
        return safetyService.getAllSafetyGoals();
    }

    // Create FSR
    @GetMapping("/fsr")
    public FunctionalSafetyRequirement createFSR(@RequestParam String name) {
        return safetyService.createFSR(name);
    }

    // List all FSRs
    @GetMapping("/fsr/all")
    public List<FunctionalSafetyRequirement> getAllFSRs() {
        return safetyService.getAllFSRs();
    }

    // Link SG ↔ FSR
    @GetMapping("/link")
    public String linkSgToFsr(@RequestParam String sgId,
                              @RequestParam String fsrId) {
        safetyService.linkSGtoFSR(sgId, fsrId);
        return "Link created successfully";
    }
}
