package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {
    
    private final AssetLifecycleEventRepository eventRepository;
    private final AssetRepository assetRepository;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepository, 
                                        AssetRepository assetRepository) {
        this.eventRepository = eventRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        if (event.getEventType() == null || event.getEventType().trim().isEmpty()) {
            throw new IllegalArgumentException("Event type is required");
        }
        
        if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Event description cannot be blank");
        }
        
        if (event.getEventDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Event date cannot be in the future");
        }
        
        event.setAsset(asset);
        event.setLoggedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        if (!assetRepository.existsById(assetId)) {
            throw new ResourceNotFoundException("Asset not found");
        }
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }

    @Override
    public AssetLifecycleEvent getEvent(Long id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public AssetLifecycleEvent updateEvent(Long id, AssetLifecycleEvent event) {
        AssetLifecycleEvent existingEvent = getEvent(id);
        existingEvent.setEventType(event.getEventType());
        existingEvent.setEventDescription(event.getEventDescription());
        existingEvent.setEventDate(event.getEventDate());
        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        AssetLifecycleEvent event = getEvent(id);
        eventRepository.delete(event);
    }
}