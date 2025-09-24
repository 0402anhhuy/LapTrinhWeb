package com.ltw.bt07.service;

import com.ltw.bt07.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Page<Category> getAllCategories(Pageable pageable);

    Optional<Category> getCategoryById(Integer id);
    Optional<Category> getCategoryByName(String name);

    Category save(Category category);
    void delete(Integer id);
}
