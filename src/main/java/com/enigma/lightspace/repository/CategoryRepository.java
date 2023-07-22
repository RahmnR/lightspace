package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByCategoryIgnoreCase(String category);
}
