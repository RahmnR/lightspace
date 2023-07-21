package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
