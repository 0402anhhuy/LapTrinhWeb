package com.ltw.trananhhuy_23110229.controller;

import com.ltw.trananhhuy_23110229.entity.User_23110229;
import com.ltw.trananhhuy_23110229.service.UserService_23110229;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController_23110229 {
    private final UserService_23110229 userService_23110229;

    @GetMapping("/login")
    public String showLoginForm_23110229() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login_23110229(@RequestParam String username_23110229,
                                 @RequestParam String password_23110229,
                                 HttpSession session_23110229,
                                 Model model_23110229) {
        User_23110229 user_23110229 = userService_23110229.findById_23110229(username_23110229).orElse(null);
        if (user_23110229 != null
                && Boolean.TRUE.equals(user_23110229.getActive_23110229())
                && password_23110229.equals(user_23110229.getPassword_23110229())) {
            session_23110229.setAttribute("user_23110229", user_23110229);
            return "redirect:/";
        } else {
            model_23110229.addAttribute("error_23110229", "Invalid username or password");
            return "auth/login";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm_23110229() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register_23110229(@RequestParam String username_23110229,
                                    @RequestParam String password_23110229,
                                    @RequestParam String fullname_23110229,
                                    @RequestParam String email_23110229,
                                    Model model_23110229) {
        try {
            if (userService_23110229.findById_23110229(username_23110229).isPresent()) {
                model_23110229.addAttribute("error_23110229", "Username already exists");
                return "auth/register";
            }

            User_23110229 user_23110229 = new User_23110229();
            user_23110229.setUsername_23110229(username_23110229);
            user_23110229.setPassword_23110229(password_23110229);
            user_23110229.setFullname_23110229(fullname_23110229);
            user_23110229.setEmail_23110229(email_23110229);
            user_23110229.setAdmin_23110229(false);
            user_23110229.setActive_23110229(true);

            userService_23110229.save_23110229(user_23110229);

            model_23110229.addAttribute("success_23110229", "Registration successful! Please login.");
            return "auth/login";
        } catch (Exception e) {
            model_23110229.addAttribute("error_23110229", e.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/logout")
    public String logout_23110229(HttpSession session_23110229) {
        session_23110229.removeAttribute("user_23110229");
        session_23110229.invalidate();
        return "redirect:/auth/login";
    }
}