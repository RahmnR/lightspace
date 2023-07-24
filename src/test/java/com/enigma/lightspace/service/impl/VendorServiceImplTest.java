package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Vendor;
import com.enigma.lightspace.model.request.VendorRequest;
import com.enigma.lightspace.model.response.VendorResponse;
import com.enigma.lightspace.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {
    @Mock
    private VendorRepository vendorRepository;

    private VendorServiceImpl vendorService;

    @BeforeEach
    void setUp() {
        this.vendorService = new VendorServiceImpl(vendorRepository);
    }

    @Test
    public void testCreateVendor() {
        Vendor vendor = new Vendor();
        vendor.setName("Vendor A");
        vendor.setPhone("1234567890");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        Vendor result = vendorService.create(vendor);

        assertEquals("Vendor A", result.getName());
        assertEquals("1234567890", result.getPhone());
    }

    @Test
    public void testGetByAuth() {
        Vendor vendor = Vendor.builder()
                .name("Vendor A")
                .phone("1234567890")
                .build();

        when(vendorRepository.findByUserCredential_Email("vendor@example.com")).thenReturn(Optional.of(vendor));

        Authentication authentication = new TestingAuthenticationToken("vendor@example.com", "password");

        Vendor result = vendorService.getByAuth(authentication);

        assertEquals("Vendor A", result.getName());
        assertEquals("1234567890", result.getPhone());
    }

    @Test
    public void testGetById() {
        Vendor vendor = new Vendor();
        vendor.setId("vendorId");
        vendor.setName("Vendor A");
        vendor.setPhone("1234567890");

        when(vendorRepository.findById("vendorId")).thenReturn(Optional.of(vendor));

        Vendor result = vendorService.getById("vendorId");

        assertEquals("vendorId", result.getId());
        assertEquals("Vendor A", result.getName());
        assertEquals("1234567890", result.getPhone());
    }

    @Test
    public void testGetAll() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor A");
        vendor1.setPhone("1234567890");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor B");
        vendor2.setPhone("9876543210");

        List<Vendor> vendors = new ArrayList<>();
        vendors.add(vendor1);
        vendors.add(vendor2);

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorResponse> result = vendorService.getAll();

        assertEquals(2, result.size());
        assertEquals("Vendor A", result.get(0).getName());
        assertEquals("1234567890", result.get(0).getPhone());
        assertEquals("Vendor B", result.get(1).getName());
        assertEquals("9876543210", result.get(1).getPhone());
    }


    @Test
    public void testUpdate() {
        Vendor vendor = new Vendor();
        vendor.setName("Vendor A");
        vendor.setPhone("1234567890");

        when(vendorRepository.findByUserCredential_Email("vendor@example.com")).thenReturn(Optional.of(vendor));

        Authentication authentication = new TestingAuthenticationToken("vendor@example.com", "password");

        VendorRequest request = new VendorRequest();
        request.setName("Updated Vendor");
        request.setPhone("5555555555");

        VendorResponse result = vendorService.update(request, authentication);

        assertEquals("Updated Vendor", result.getName());
        assertEquals("5555555555", result.getPhone());
    }

    @Test
    public void testDeleteById() {
        Vendor vendor = new Vendor();
        vendor.setId("vendorId");
        vendor.setName("Vendor A");
        vendor.setPhone("1234567890");

        when(vendorRepository.findById("vendorId")).thenReturn(Optional.of(vendor));

        vendorService.deleteByid("vendorId");

        verify(vendorRepository, times(1)).delete(vendor);
    }
}