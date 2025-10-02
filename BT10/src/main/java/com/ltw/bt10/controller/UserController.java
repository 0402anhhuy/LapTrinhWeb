package com.ltw.bt10.controller;

import com.ltw.bt10.entity.User;
import com.ltw.bt10.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ProductService productService;

    @GetMapping("/dashboard")
    public String userDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"USER".equals(user.getRole().name())) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("products", productService.findAll());
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile(@Valid Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"USER".equals(user.getRole().name())) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "user/profile";
    }
}
