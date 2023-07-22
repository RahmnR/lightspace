package com.enigma.lightspace.service;

import com.enigma.lightspace.entity.Category;

public interface CategoryService {
    public Category getOrSave(String category);
}
