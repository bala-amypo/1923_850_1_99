package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {
    
    private final AssetLifecycleEventService eventService;

    public AssetLifecycleEventController(AssetLifecycleEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> logEvent(@PathVariable Long assetId, 
                                                       @RequestBody AssetLifecycleEvent event) {
        AssetLifecycleEvent loggedEvent = eventService.logEvent(assetId, event);
        return ResponseEntity.ok(loggedEvent);
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        List<AssetLifecycleEvent> events = eventService.getEventsForAsset(assetId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetLifecycleEvent> getEvent(@PathVariable Long id) {
        AssetLifecycleEvent event = eventService.getEvent(id);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetLifecycleEvent> updateEvent(@PathVariable Long id, 
                                                          @RequestBody AssetLifecycleEvent event) {
        AssetLifecycleEvent updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}