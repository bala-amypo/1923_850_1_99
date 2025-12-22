package com.example.demo.service;

import com.example.demo.entity.Asset;
import java.util.List;

public interface AssetService {
    Asset createAsset(Long vendorId, Long ruleId, Asset asset);
    List<Asset> getAssetsByStatus(String status);
    List<Asset> getAllAssets();
    Asset getAsset(Long id);
    Asset updateAsset(Long id, Asset asset);
    void deleteAsset(Long id);
}