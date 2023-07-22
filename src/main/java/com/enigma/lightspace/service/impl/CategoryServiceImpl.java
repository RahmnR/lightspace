package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Category;
import com.enigma.lightspace.repository.CategoryRepository;
import com.enigma.lightspace.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category getOrSave(String category) {
        return categoryRepository.findByCategoryIgnoreCase(category).orElseGet(() ->
                categoryRepository.save(Category.builder().category(category).build()));
    }
}

