package com.ltw.bt07.repository;

import com.ltw.bt07.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findAllBy(Pageable pageable);

    Optional<Category> findByName(String name);
}
