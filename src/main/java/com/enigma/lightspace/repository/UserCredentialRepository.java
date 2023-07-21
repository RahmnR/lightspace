package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential,String> {
}
