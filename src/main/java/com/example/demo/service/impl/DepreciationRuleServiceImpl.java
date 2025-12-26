package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository repository;

    @Autowired
    public DepreciationRuleServiceImpl(DepreciationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        // Validation logic omitted for brevity in overwrite but assumed present as per
        // previous step or needs re-inclusion?
        // Wait, overwrite replaces file. I MUST include validation logic again!
        if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Useful life must be > 0");
        }
        if (rule.getSalvageValue() != null && rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Salvage value must be >= 0");
        }
        if (!"STRAIGHT_LINE".equals(rule.getMethod()) && !"DECLINING_BALANCE".equals(rule.getMethod())) {
            throw new IllegalArgumentException("Invalid depreciation method");
        }
        if (repository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Rule name already exists");
        }
        return repository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return repository.findAll();
    }
}
