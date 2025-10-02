package com.ltw.bt10.service;

import com.ltw.bt10.dto.ProductDTO;
import com.ltw.bt10.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(ProductDTO productDTO);
    Product update(Long id, ProductDTO productDTO);
    void deleteById(Long id);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameContaining(String name);
    Product convertToEntity(ProductDTO productDTO);
    ProductDTO convertToDTO(Product product);
}
