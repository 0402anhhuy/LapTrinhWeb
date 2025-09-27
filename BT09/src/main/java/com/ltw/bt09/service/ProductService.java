package com.ltw.bt09.service;

import com.ltw.bt09.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllBy();
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByCategories_Id(Long id);
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
