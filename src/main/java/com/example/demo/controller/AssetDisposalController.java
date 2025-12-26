package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    @Autowired
    private AssetDisposalRepository disposalRepository;
    
    @Autowired
    private AssetRepository assetRepository;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(@PathVariable Long assetId, @RequestBody AssetDisposal disposal) {
        try {
            Asset asset = assetRepository.findById(assetId).orElse(null);
            if (asset == null) {
                return ResponseEntity.badRequest().build();
            }
            
            disposal.setAsset(asset);
            AssetDisposal saved = disposalRepository.save(disposal);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/approve/{disposalId}/{userId}")
    public ResponseEntity<AssetDisposal> approveDisposal(@PathVariable Long disposalId, @PathVariable Long userId) {
        try {
            AssetDisposal disposal = disposalRepository.findById(disposalId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
            
            if (disposal == null || user == null) {
                return ResponseEntity.badRequest().build();
            }
            
            // Check if user has ADMIN role
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
            if (!isAdmin) {
                return ResponseEntity.status(403).build();
            }
            
            disposal.setApprovedBy(user);
            AssetDisposal saved = disposalRepository.save(disposal);
            
            // Update asset status to DISPOSED
            Asset asset = disposal.getAsset();
            asset.setStatus("DISPOSED");
            assetRepository.save(asset);
            
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}