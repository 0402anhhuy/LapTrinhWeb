package com.ltw.bt07.service;

import com.ltw.bt07.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Page<Category> findAllBy(Pageable pageable);
    Category getCategoryByName(String name);
    Category getCategoryById(Integer id);
    void saveCategory(Category category);
    void deleteCategoryByName(String name);
    void deleteCategoryById(Integer id);
}
