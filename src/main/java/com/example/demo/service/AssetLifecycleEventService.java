package com.example.demo.service;

import com.example.demo.entity.AssetLifecycleEvent;
import java.util.List;

public interface AssetLifecycleEventService {
    AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event);
    List<AssetLifecycleEvent> getEventsForAsset(Long assetId);
    AssetLifecycleEvent getEvent(Long id);
    AssetLifecycleEvent updateEvent(Long id, AssetLifecycleEvent event);
    void deleteEvent(Long id);
}