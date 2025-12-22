package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (vendorRepository.findByVendorName(vendor.getVendorName()).isPresent()) {
            throw new IllegalArgumentException("Vendor name already exists");
        }
        
        if (vendor.getContactEmail() != null && !vendor.getContactEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        vendor.setCreatedAt(LocalDateTime.now());
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendor(Long id) {
        return vendorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor existingVendor = getVendor(id);
        existingVendor.setVendorName(vendor.getVendorName());
        existingVendor.setContactEmail(vendor.getContactEmail());
        existingVendor.setPhone(vendor.getPhone());
        return vendorRepository.save(existingVendor);
    }

    @Override
    public void deleteVendor(Long id) {
        Vendor vendor = getVendor(id);
        vendorRepository.delete(vendor);
    }
}