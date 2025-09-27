package com.ltw.bt09.repository;

import com.ltw.bt09.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllBy();
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByCategories_Id(Long id);
    Product findProductById(Long id);
}