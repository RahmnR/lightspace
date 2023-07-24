package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Admin;
import com.enigma.lightspace.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    private AdminRepository adminRepository;
    private AdminServiceImpl adminService;
    @Test
    void create() {
        adminService = new AdminServiceImpl(adminRepository);

        Admin admin = Admin.builder().email("email").name("admin").build();
        when(adminRepository.save(admin)).thenReturn(admin);
        Admin result = adminService.create(admin);

        assertEquals("admin",result.getName());
        assertEquals("email",result.getEmail());
    }
}