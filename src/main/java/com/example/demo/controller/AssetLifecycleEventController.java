package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    @Autowired
    private AssetLifecycleEventRepository eventRepository;
    
    @Autowired
    private AssetRepository assetRepository;

    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> logEvent(@PathVariable Long assetId, @RequestBody AssetLifecycleEvent event) {
        try {
            if (event.getEventDate().isAfter(LocalDate.now())) {
                return ResponseEntity.status(401).build();
            }
            
            if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            Asset asset = assetRepository.findById(assetId).orElse(null);
            if (asset == null) {
                return ResponseEntity.badRequest().build();
            }
            
            event.setAsset(asset);
            AssetLifecycleEvent saved = eventRepository.save(event);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(eventRepository.findByAssetIdOrderByEventDateDesc(assetId));
    }
}