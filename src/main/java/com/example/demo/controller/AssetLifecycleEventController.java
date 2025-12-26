package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AssetLifecycleEventController {
    
    private final AssetLifecycleEventService eventService;
    
    public AssetLifecycleEventController(AssetLifecycleEventService eventService) {
        this.eventService = eventService;
    }
    
    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> logEvent(@PathVariable Long assetId, 
                                                       @RequestBody AssetLifecycleEvent event) {
        try {
            AssetLifecycleEvent loggedEvent = eventService.logEvent(assetId, event);
            return ResponseEntity.ok(loggedEvent);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        try {
            List<AssetLifecycleEvent> events = eventService.getEventsForAsset(assetId);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get events");
        }
    }
}