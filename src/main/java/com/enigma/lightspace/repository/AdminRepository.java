package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
}
