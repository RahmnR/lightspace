package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Category;
import com.enigma.lightspace.entity.Role;
import com.enigma.lightspace.entity.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByRole(ERole category);

}
