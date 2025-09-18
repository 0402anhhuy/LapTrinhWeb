package com.ltw.bt07.controller;

import com.ltw.bt07.entity.Category;
import com.ltw.bt07.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String listCategories(@RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "5") int size,
                                 Model model) {
        Page<Category> categoriesPage = categoryService.findAllBy(PageRequest.of(page, size));
        model.addAttribute("categoriesPage", categoriesPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "list-category";
    }

    @GetMapping("/view/{id}")
    public String viewCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categories/view";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categories/edit";
    }

    @PostMapping("/edit")
    public String saveOrUpdate(@ModelAttribute Category category,
                               @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = "src/main/resources/static/images/";
            String fileName = imageFile.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, imageFile.getBytes());
            category.setImage(fileName);
        }
        else {
            Category old = categoryService.getCategoryById(category.getId());
            category.setImage(old.getImage());
        }
        categoryService.saveCategory(category);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/add";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category,
                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String uploadDir = "src/main/resources/static/images/";
            String fileName = imageFile.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, imageFile.getBytes());
            category.setImage(fileName);
        }
        categoryService.saveCategory(category);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String confirmDelete(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categories/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchCategory(@RequestParam("keyword") String name, Model model) {
        Category category = categoryService.getCategoryByName(name);
        model.addAttribute("category", category);
        return "categories/view";
    }
}
