package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    
    private final AssetService assetService;
    
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    
    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<Asset> createAsset(@PathVariable Long vendorId, 
                                           @PathVariable Long ruleId, 
                                           @RequestBody Asset asset) {
        try {
            Asset createdAsset = assetService.createAsset(vendorId, ruleId, asset);
            return ResponseEntity.ok(createdAsset);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        try {
            List<Asset> assets = assetService.getAllAssets();
            return ResponseEntity.ok(assets);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get assets");
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Asset>> getAssetsByStatus(@PathVariable String status) {
        try {
            List<Asset> assets = assetService.getAssetsByStatus(status);
            return ResponseEntity.ok(assets);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get assets by status");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAsset(@PathVariable Long id) {
        try {
            Asset asset = assetService.getAsset(id);
            return ResponseEntity.ok(asset);
        } catch (Exception e) {
            throw new RuntimeException("Asset not found");
        }
    }
}