package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Admin;
import com.enigma.lightspace.repository.AdminRepository;
import com.enigma.lightspace.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }
}
