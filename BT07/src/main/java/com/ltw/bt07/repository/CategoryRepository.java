package com.ltw.bt07.repository;

import com.ltw.bt07.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllBy();
    Page<Category> findAllBy(Pageable pageable);
    Category findCategoryByName(String name);
    Category findCategoryById(Integer id);
}
