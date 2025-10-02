package com.ltw.bt10.service;

import com.ltw.bt10.dto.CategoryDTO;
import com.ltw.bt10.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(CategoryDTO categoryDTO);
    Category update(Long id, CategoryDTO categoryDTO);
    void deleteById(Long id);
    boolean existsByName(String name);
    Category convertToEntity(CategoryDTO categoryDTO);
    CategoryDTO convertToDTO(Category category);
}
