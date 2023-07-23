package com.enigma.lightspace.service;

import com.enigma.lightspace.entity.Vendor;
import com.enigma.lightspace.model.request.VendorRequest;
import com.enigma.lightspace.model.response.VendorResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VendorService {
    Vendor create(Vendor vendor);
    Vendor getByAuth(Authentication authentication);
    Vendor getById(String id);
    List<VendorResponse> getAll();
    VendorResponse update(VendorRequest request, Authentication authentication);
    void deleteByid(String id);

}
