package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String assetTag;

    private String assetName;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private LocalDate purchaseDate;
    private Double purchaseCost;

    @ManyToOne
    @JoinColumn(name = "depreciation_rule_id")
    private DepreciationRule depreciationRule;

    // ACTIVE, MAINTENANCE, DISPOSED
    private String status;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    private List<AssetLifecycleEvent> events = new ArrayList<>();

    @OneToOne(mappedBy = "asset", cascade = CascadeType.ALL)
    private AssetDisposal disposal;

    public Asset() {
    }

    public Asset(String assetTag, String assetName, Vendor vendor, LocalDate purchaseDate, Double purchaseCost,
            DepreciationRule depreciationRule) {
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.vendor = vendor;
        this.purchaseDate = purchaseDate;
        this.purchaseCost = purchaseCost;
        this.depreciationRule = depreciationRule;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "ACTIVE";
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public DepreciationRule getDepreciationRule() {
        return depreciationRule;
    }

    public void setDepreciationRule(DepreciationRule depreciationRule) {
        this.depreciationRule = depreciationRule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<AssetLifecycleEvent> getEvents() {
        return events;
    }

    public void setEvents(List<AssetLifecycleEvent> events) {
        this.events = events;
    }

    public AssetDisposal getDisposal() {
        return disposal;
    }

    public void setDisposal(AssetDisposal disposal) {
        this.disposal = disposal;
    }
}
