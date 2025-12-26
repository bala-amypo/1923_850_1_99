package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;
    
    @Autowired
    private VendorRepository vendorRepository;
    
    @Autowired
    private DepreciationRuleRepository ruleRepository;

    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<Asset> createAsset(@PathVariable Long vendorId, @PathVariable Long ruleId, @RequestBody Asset asset) {
        try {
            if (asset.getPurchaseCost() < 0) {
                return ResponseEntity.status(500).build();
            }
            
            Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
            DepreciationRule rule = ruleRepository.findById(ruleId).orElse(null);
            
            if (vendor == null || rule == null) {
                return ResponseEntity.badRequest().build();
            }
            
            asset.setVendor(vendor);
            asset.setDepreciationRule(rule);
            
            Asset saved = assetRepository.save(asset);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAsset(@PathVariable Long id) {
        return assetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Asset>> getAssetsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(assetRepository.findByStatus(status));
    }
}