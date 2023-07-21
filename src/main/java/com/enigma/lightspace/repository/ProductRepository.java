package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
