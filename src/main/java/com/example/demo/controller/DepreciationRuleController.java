package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    @Autowired
    private DepreciationRuleService ruleService;

    @PostMapping
    public ResponseEntity<DepreciationRule> createRule(@RequestBody DepreciationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<DepreciationRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
