package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    Optional<Product> findByCodeAndStatus(String code,Boolean status);
//    Optional<Product> findProductBy(String code);

}