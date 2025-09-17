package com.ltw.bt06.repository;

import com.ltw.bt06.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Integer>{
    List<Category> findAllBy();
    List<Category> findAllByUser_Username(String userUsername);
    Category findCategoryById(Integer id);
    Category findByName(String name);
}
