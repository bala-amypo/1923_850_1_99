package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "depreciation_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String method;
    private Integer usefulLifeYears;
    private Double salvageValue;
    private LocalDateTime createdAt;

    public DepreciationRule() {}

    public DepreciationRule(String ruleName, String method, Integer usefulLifeYears, Double salvageValue) {
        this.ruleName = ruleName;
        this.method = method;
        this.usefulLifeYears = usefulLifeYears;
        this.salvageValue = salvageValue;
    }

    public Long getId() { return id; }
    public String getRuleName() { return ruleName; }
    public String getMethod() { return method; }
    public Integer getUsefulLifeYears() { return usefulLifeYears; }
    public Double getSalvageValue() { return salvageValue; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
