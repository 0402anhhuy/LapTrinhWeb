package com.ltw.bt10.controller;

import com.ltw.bt10.dto.CategoryDTO;
import com.ltw.bt10.dto.ProductDTO;
import com.ltw.bt10.entity.User;
import com.ltw.bt10.service.CategoryService;
import com.ltw.bt10.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"ADMIN".equals(user.getRole().name())) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "admin/dashboard";
    }

    @GetMapping("/categories")
    public String manageCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/categories";
    }

    @GetMapping("/categories/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "admin/category-form";
    }

    @PostMapping("/categories")
    public String addCategory(@Valid @ModelAttribute("categoryDTO") CategoryDTO categoryDTO,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/category-form";
        }

        if (categoryService.existsByName(categoryDTO.getName())) {
            bindingResult.rejectValue("name", "error.categoryDTO", "Category name already exists");
            return "admin/category-form";
        }

        categoryService.save(categoryDTO);
        return "redirect:/admin/categories";
    }

    // Product Management
    @GetMapping("/products")
    public String manageProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product-form";
    }

    @PostMapping("/products")
    public String addProduct(@Valid @ModelAttribute("productDTO") ProductDTO productDTO,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "admin/product-form";
        }

        productService.save(productDTO);
        return "redirect:/admin/products";
    }
}
