package com.ltw.bt06.service.impl;

import com.ltw.bt06.entity.Category;
import com.ltw.bt06.repository.CategoryRepository;
import com.ltw.bt06.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAllBy();
    }

    @Override
    public List<Category> getAllCategoriesByUser_Username(String username) {
        return categoryRepository.findAllByUser_Username(username);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }
}
