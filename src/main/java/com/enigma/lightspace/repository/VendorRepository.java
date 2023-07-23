package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor,String> {
    Optional<Vendor> findByUserCredential_Email(String email);
}
