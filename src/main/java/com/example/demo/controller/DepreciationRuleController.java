package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    @Autowired
    private DepreciationRuleRepository ruleRepository;

    @PostMapping
    public ResponseEntity<DepreciationRule> createRule(@RequestBody DepreciationRule rule) {
        try {
            if (rule.getUsefulLifeYears() <= 0 || rule.getSalvageValue() < 0) {
                return ResponseEntity.badRequest().build();
            }
            if (!isValidMethod(rule.getMethod())) {
                return ResponseEntity.badRequest().build();
            }
            DepreciationRule saved = ruleRepository.save(rule);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DepreciationRule>> getAllRules() {
        return ResponseEntity.ok(ruleRepository.findAll());
    }

    private boolean isValidMethod(String method) {
        return "STRAIGHT_LINE".equals(method) || "DECLINING_BALANCE".equals(method);
    }
}