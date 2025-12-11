package com.example.demo.controller;

import com.example.demo.model.SafetyGoal;
import com.example.demo.model.FunctionalSafetyRequirement;
import com.example.demo.repo.SafetyGoalRepo;
import com.example.demo.repo.FunctionalSafetyRequirementRepo;
import com.example.demo.service.SafetyService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SafetyController {

    private final SafetyGoalRepo sgRepo;
    private final FunctionalSafetyRequirementRepo fsrRepo;
    private final SafetyService safetyService;

    public SafetyController(SafetyGoalRepo sgRepo,
                            FunctionalSafetyRequirementRepo fsrRepo,
                            SafetyService safetyService) {
        this.sgRepo = sgRepo;
        this.fsrRepo = fsrRepo;
        this.safetyService = safetyService;
    }

    @PostMapping("/sg")
    public SafetyGoal createSG(@RequestBody SafetyGoal sg) {
        return sgRepo.save(sg);
    }

    @PostMapping("/fsr")
    public FunctionalSafetyRequirement createFSR(@RequestBody FunctionalSafetyRequirement fsr) {
        return fsrRepo.save(fsr);
    }

    @PostMapping("/link")
    public String link(@RequestParam String sgId, @RequestParam String fsrId) {
        safetyService.linkSGtoFSR(sgId, fsrId);
        return "Linked SG " + sgId + " → FSR " + fsrId;
    }

    @GetMapping("/sg")
    public List<SafetyGoal> getSGs() { return sgRepo.findAll(); }

    @GetMapping("/fsr")
    public List<FunctionalSafetyRequirement> getFSRs() { return fsrRepo.findAll(); }
}