package com.ltw.bt09.service.impl;

import com.ltw.bt09.entity.Category;
import com.ltw.bt09.repository.CategoryRepository;
import com.ltw.bt09.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllBy() {
        return categoryRepository.findAllBy();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
