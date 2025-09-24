package com.ltw.bt07.service;

import com.ltw.bt07.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable);
    Page<Product> getProductsByCategoryName(String name, Pageable pageable);

    Optional<Product> getProductById(Integer id);
    Optional<Product> getProductByName(String name);

    Product save(Product product);
    void delete(Integer id);
}

