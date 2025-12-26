// package com.example.demo.repository;

// import com.example.demo.entity.AssetDisposal;
// import com.example.demo.entity.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface AssetDisposalRepository extends JpaRepository<AssetDisposal, Long> {
//     List<AssetDisposal> findByApprovedBy(User approvedBy);
// 

package com.example.demo.repository;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetDisposalRepository extends JpaRepository<AssetDisposal, Long> {
    List<AssetDisposal> findByApprovedBy(User approvedBy);
    List<AssetDisposal> findByAsset_Vendor(Vendor vendor); // note underscore for nested property
}
