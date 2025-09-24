package com.ltw.bt07.controller;

import com.ltw.bt07.entity.Product;
import com.ltw.bt07.response.Response;
import com.ltw.bt07.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Response> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        Page<Product> products = productService.getAllProducts(PageRequest.of(page, size));
        return ResponseEntity.ok(new Response(true, "Danh sách sản phẩm", products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(p -> ResponseEntity.ok(new Response(true, "OK", p)))
                .orElse(new ResponseEntity<>(new Response(false, "Không tìm thấy Product", null), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Product product) {
        Product saved = productService.save(product);
        return new ResponseEntity<>(new Response(true, "Tạo Product thành công", saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody Product product) {
        return productService.getProductById(id).map(existing -> {
            product.setId(id);
            Product updated = productService.save(product);
            return ResponseEntity.ok(new Response(true, "Cập nhật thành công", updated));
        }).orElse(new ResponseEntity<>(new Response(false, "Không tìm thấy Product", null), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.ok(new Response(true, "Xóa Product thành công", null));
    }
}

