package com.ltw.bt10.controller;

import com.ltw.bt10.dto.LoginDTO;
import com.ltw.bt10.dto.UserDTO;
import com.ltw.bt10.entity.User;
import com.ltw.bt10.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
                        BindingResult bindingResult,
                        HttpSession session,
                        Model model) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword())
                .orElse(null);

        if (user != null) {
            session.setAttribute("loggedInUser", user);

            if ("ADMIN".equals(user.getRole().name())) {
                return "redirect:/admin/dashboard";
            } else if ("USER".equals(user.getRole().name())) {
                return "redirect:/user/dashboard";
            }
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.existsByUsername(userDTO.getUsername())) {
            bindingResult.rejectValue("username", "error.userDTO", "Username already exists");
            return "register";
        }

        if (userService.existsByEmail(userDTO.getEmail())) {
            bindingResult.rejectValue("email", "error.userDTO", "Email already exists");
            return "register";
        }

        userService.saveUser(userDTO);
        model.addAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
