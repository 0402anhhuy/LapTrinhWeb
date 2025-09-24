package com.ltw.bt07.controller;

import com.ltw.bt07.entity.Category;
import com.ltw.bt07.response.Response;
import com.ltw.bt07.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Response> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Category> categories = categoryService.getAllCategories(PageRequest.of(page, size));
        return ResponseEntity.ok(new Response(true, "Danh sách Category", categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id)
                .map(c -> ResponseEntity.ok(new Response(true, "OK", c)))
                .orElse(new ResponseEntity<>(new Response(false, "Không tìm thấy Category", null), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Category category) {
        Category saved = categoryService.save(category);
        return new ResponseEntity<>(new Response(true, "Tạo Category thành công", saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody Category category) {
        return categoryService.getCategoryById(id).map(existing -> {
            category.setId(id);
            Category updated = categoryService.save(category);
            return ResponseEntity.ok(new Response(true, "Cập nhật thành công", updated));
        }).orElse(new ResponseEntity<>(new Response(false, "Không tìm thấy Category", null), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok(new Response(true, "Xóa Category thành công", null));
    }
}
