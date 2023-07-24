package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Category;
import com.enigma.lightspace.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryServiceImpl categoryService;
    @Test
    void getOrSave() {
        categoryService = new CategoryServiceImpl(categoryRepository);
        Category category = new Category();
        category.setId("1");
        category.setCategory("Category");

        when(categoryRepository.findByCategoryIgnoreCase("Category")).thenReturn(Optional.of(category));

        Category result = categoryService.getOrSave("Category");

        assertEquals("1", result.getId());
        assertEquals("Category", result.getCategory());
    }

}