package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {
    
    private final AssetDisposalService disposalService;

    public AssetDisposalController(AssetDisposalService disposalService) {
        this.disposalService = disposalService;
    }

    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(@PathVariable Long assetId, 
                                                        @RequestBody AssetDisposal disposal) {
        AssetDisposal requestedDisposal = disposalService.requestDisposal(assetId, disposal);
        return ResponseEntity.ok(requestedDisposal);
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(@PathVariable Long disposalId, 
                                                        @PathVariable Long adminId) {
        AssetDisposal approvedDisposal = disposalService.approveDisposal(disposalId, adminId);
        return ResponseEntity.ok(approvedDisposal);
    }

    @GetMapping
    public ResponseEntity<List<AssetDisposal>> getAllDisposals() {
        List<AssetDisposal> disposals = disposalService.getAllDisposals();
        return ResponseEntity.ok(disposals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDisposal> getDisposal(@PathVariable Long id) {
        AssetDisposal disposal = disposalService.getDisposal(id);
        return ResponseEntity.ok(disposal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDisposal> updateDisposal(@PathVariable Long id, 
                                                       @RequestBody AssetDisposal disposal) {
        AssetDisposal updatedDisposal = disposalService.updateDisposal(id, disposal);
        return ResponseEntity.ok(updatedDisposal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisposal(@PathVariable Long id) {
        disposalService.deleteDisposal(id);
        return ResponseEntity.noContent().build();
    }
}