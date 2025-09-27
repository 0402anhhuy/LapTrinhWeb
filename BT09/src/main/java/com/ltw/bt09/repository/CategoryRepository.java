package com.ltw.bt09.repository;

import com.ltw.bt09.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long>{
    List<Category> findAllBy();
    Category findCategoryById(Long id);
}
