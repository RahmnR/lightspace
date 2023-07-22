package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Vendor;
import com.enigma.lightspace.repository.VendorRepository;
import com.enigma.lightspace.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    @Override
    public Vendor create(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor getById(String id) {
        return vendorRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor update(Vendor vendor) {
        Vendor data = getById(vendor.getId());
        if (data!=null)return vendorRepository.save(vendor);
        return null;
    }

    @Override
    public void deleteByid(String id) {
        Vendor data = getById(id);
        vendorRepository.delete(data);
    }
}
