package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;
import java.util.List;

public interface AssetDisposalService {
    AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal);
    AssetDisposal approveDisposal(Long disposalId, Long adminId);
    List<AssetDisposal> getAllDisposals();
    AssetDisposal getDisposal(Long id);
    AssetDisposal updateDisposal(Long id, AssetDisposal disposal);
    void deleteDisposal(Long id);
}