package com.ltw.bt06.controller;

import com.ltw.bt06.entity.Category;
import com.ltw.bt06.entity.User;
import com.ltw.bt06.service.CategoryService;
import com.ltw.bt06.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/list")
    public String List(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "list";
    }

    @GetMapping("/add")
    public String Add(){
        return "add";
    }

    @PostMapping("/add")
    public String doAdd(@RequestParam("name") String name, @RequestParam("icon") MultipartFile icon, HttpSession session, RedirectAttributes ra) {
        try {
            String username = (String) session.getAttribute("AUTH_USER");
            if (username == null) {
                ra.addFlashAttribute("error", "Vui lòng đăng nhập trước khi thêm danh mục.");
                return "redirect:/login";
            }
            User user = userService.getUserByUsername(username);
            if (user == null) {
                ra.addFlashAttribute("error", "Không tìm thấy thông tin người dùng.");
                return "redirect:/login";
            }

            if (name == null || name.trim().isEmpty()) {
                ra.addFlashAttribute("error", "Tên danh mục không được để trống.");
                return "redirect:/add";
            }
            if (icon == null || icon.isEmpty()) {
                ra.addFlashAttribute("error", "Vui lòng chọn ảnh danh mục.");
                return "redirect:/add";
            }

            Path uploadDir = Paths.get("src/main/resources/static/images/categories");
            Files.createDirectories(uploadDir);
            String original = icon.getOriginalFilename() != null ? icon.getOriginalFilename() : "image";
            String ext = original.contains(".") ? original.substring(original.lastIndexOf(".")) : "";
            String storedName = UUID.randomUUID() + ext;
            Path storedPath = uploadDir.resolve(storedName);
            icon.transferTo(storedPath.toFile());

            Category c = new Category();
            c.setName(name.trim());
            c.setImage(storedName);
            c.setUser(user);

            categoryService.saveCategory(c);

            ra.addFlashAttribute("success", "Thêm danh mục thành công.");
            return "redirect:/list";
        } catch (IOException e) {
            ra.addFlashAttribute("error", "Lỗi lưu file ảnh: " + e.getMessage());
            return "redirect:/add";
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Đã xảy ra lỗi: " + ex.getMessage());
            return "redirect:/add";
        }
    }

    @GetMapping("/edit")
    public String Edit(@RequestParam("id") Integer id, Model model, RedirectAttributes ra) {
        Category c = categoryService.getCategoryById(id);
        if (c == null) {
            ra.addFlashAttribute("error", "Không tìm thấy danh mục.");
            return "redirect:/list";
        }
        model.addAttribute("category", c);
        return "edit";
    }

    @PostMapping("/edit")
    public String doEdit(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam(value = "icon", required = false) MultipartFile icon, RedirectAttributes ra) {
        try {
            Category c = categoryService.getCategoryById(id);
            if (c == null) {
                ra.addFlashAttribute("error", "Không tìm thấy danh mục.");
                return "redirect:/list";
            }
            if (name == null || name.trim().isEmpty()) {
                ra.addFlashAttribute("error", "Tên danh mục không được để trống.");
                return "redirect:/edit?id=" + id;
            }

            c.setName(name.trim());

            if (icon != null && !icon.isEmpty()) {
                Path uploadDir = Paths.get("src/main/resources/static/images/categories");
                Files.createDirectories(uploadDir);
                String original = icon.getOriginalFilename() != null ? icon.getOriginalFilename() : "image";
                String ext = original.contains(".") ? original.substring(original.lastIndexOf(".")) : "";
                String storedName = UUID.randomUUID() + ext;
                Path storedPath = uploadDir.resolve(storedName);
                icon.transferTo(storedPath.toFile());
                c.setImage(storedName);
            }

            categoryService.saveCategory(c);

            ra.addFlashAttribute("success", "Cập nhật danh mục thành công.");
            return "redirect:/list";
        } catch (IOException e) {
            ra.addFlashAttribute("error", "Lỗi lưu file ảnh: " + e.getMessage());
            return "redirect:/edit?id=" + id;
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Đã xảy ra lỗi: " + ex.getMessage());
            return "redirect:/edit?id=" + id;
        }
    }

}
