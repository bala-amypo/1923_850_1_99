package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String assetTag;
    
    private String assetName;
    private LocalDate purchaseDate;
    private Double purchaseCost;
    private String status = "ACTIVE";
    private LocalDateTime createdAt;
    
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    
    @ManyToOne
    @JoinColumn(name = "depreciation_rule_id")
    private DepreciationRule depreciationRule;
    
    @OneToMany(mappedBy = "asset")
    private Set<AssetLifecycleEvent> lifecycleEvents = new HashSet<>();
    
    @OneToOne(mappedBy = "asset")
    private AssetDisposal disposal;
    
    public Asset() {}
    
    public Asset(String assetTag, String assetName, LocalDate purchaseDate, Double purchaseCost) {
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.purchaseDate = purchaseDate;
        this.purchaseCost = purchaseCost;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }
    
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    
    public Double getPurchaseCost() { return purchaseCost; }
    public void setPurchaseCost(Double purchaseCost) { this.purchaseCost = purchaseCost; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    
    public DepreciationRule getDepreciationRule() { return depreciationRule; }
    public void setDepreciationRule(DepreciationRule depreciationRule) { this.depreciationRule = depreciationRule; }
    
    public Set<AssetLifecycleEvent> getLifecycleEvents() { return lifecycleEvents; }
    public void setLifecycleEvents(Set<AssetLifecycleEvent> lifecycleEvents) { this.lifecycleEvents = lifecycleEvents; }
    
    public AssetDisposal getDisposal() { return disposal; }
    public void setDisposal(AssetDisposal disposal) { this.disposal = disposal; }
}