package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Vendor;
import com.enigma.lightspace.model.request.VendorRequest;
import com.enigma.lightspace.model.response.VendorResponse;
import com.enigma.lightspace.repository.VendorRepository;
import com.enigma.lightspace.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    @Override
    public Vendor create(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor getByAuth(Authentication authentication) {
        return vendorRepository.findByUserCredential_Email(authentication.getName()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public Vendor getById(String id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id notfound"));
    }

    @Override
    public List<VendorResponse> getAll() {
        return vendorRepository.findAll().stream()
                .map(vendor -> toResponse(vendor)).collect(Collectors.toList());
    }

    @Override
    public VendorResponse update(VendorRequest request, Authentication authentication) {
        Vendor vendor = getByAuth(authentication);
        vendor.setName(request.getName());
        vendor.setPhone(request.getPhone());
        return toResponse(vendor);
    }

    @Override
    public void deleteByid(String id) {
        Vendor data = getById(id);
        vendorRepository.delete(data);
    }

    private VendorResponse toResponse(Vendor vendor){
        return VendorResponse.builder()
                .name(vendor.getName())
                .phone(vendor.getPhone())
                .build();
    }
}
