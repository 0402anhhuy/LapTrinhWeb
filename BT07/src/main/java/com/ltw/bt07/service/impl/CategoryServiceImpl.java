package com.ltw.bt07.service.impl;

import com.ltw.bt07.entity.Category;
import com.ltw.bt07.repository.CategoryRepository;
import com.ltw.bt07.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllBy();
    }

    @Override
    public Page<Category> findAllBy(Pageable pageable) {
        return categoryRepository.findAllBy(pageable);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryByName(String name) {
        Category category = categoryRepository.findCategoryByName(name);
        categoryRepository.delete(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
