package com.ltw.bt06.service;

import com.ltw.bt06.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Category> getAllCategoriesByUser_Username(String username);
    Category getCategoryById(Integer id);
    Category getCategoryByName(String name);
    void saveCategory(Category category);
    void deleteCategory(Category category);
}
