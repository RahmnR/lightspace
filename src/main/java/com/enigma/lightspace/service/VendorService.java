package com.enigma.lightspace.service;

import com.enigma.lightspace.entity.Vendor;

import java.util.List;

public interface VendorService {
    Vendor create(Vendor vendor);
    Vendor getById(String id);
    List<Vendor> getAll();
    Vendor update(Vendor vendor);
    void deleteByid(String id);

}
