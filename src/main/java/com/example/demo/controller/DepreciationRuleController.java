package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepreciationRuleController {
    
    private final DepreciationRuleService depreciationRuleService;
    
    public DepreciationRuleController(DepreciationRuleService depreciationRuleService) {
        this.depreciationRuleService = depreciationRuleService;
    }
    
    @PostMapping
    public ResponseEntity<DepreciationRule> createRule(@RequestBody DepreciationRule rule) {
        try {
            DepreciationRule createdRule = depreciationRuleService.createRule(rule);
            return ResponseEntity.ok(createdRule);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}