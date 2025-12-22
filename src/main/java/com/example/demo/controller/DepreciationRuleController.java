package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {
    
    private final DepreciationRuleService depreciationRuleService;

    public DepreciationRuleController(DepreciationRuleService depreciationRuleService) {
        this.depreciationRuleService = depreciationRuleService;
    }

    @PostMapping
    public ResponseEntity<DepreciationRule> createRule(@RequestBody DepreciationRule rule) {
        DepreciationRule createdRule = depreciationRuleService.createRule(rule);
        return ResponseEntity.ok(createdRule);
    }

    @GetMapping
    public ResponseEntity<List<DepreciationRule>> getAllRules() {
        List<DepreciationRule> rules = depreciationRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepreciationRule> getRule(@PathVariable Long id) {
        DepreciationRule rule = depreciationRuleService.getRule(id);
        return ResponseEntity.ok(rule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepreciationRule> updateRule(@PathVariable Long id, @RequestBody DepreciationRule rule) {
        DepreciationRule updatedRule = depreciationRuleService.updateRule(id, rule);
        return ResponseEntity.ok(updatedRule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        depreciationRuleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}