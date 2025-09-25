package com.ltw.trananhhuy_23110229.repository;

import com.ltw.trananhhuy_23110229.entity.Category_23110229;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository_23110229 extends JpaRepository<Category_23110229, Integer> {

    @Query("SELECT c FROM Category_23110229 c WHERE c.status_23110229 = true")
    List<Category_23110229> findByStatus_23110229True();
}