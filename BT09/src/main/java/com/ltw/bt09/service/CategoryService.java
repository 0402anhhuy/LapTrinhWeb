package com.ltw.bt09.service;

import com.ltw.bt09.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllBy();
    Category findById(Long id);
    Category save(Category category);
    void deleteById(Long id);
}
