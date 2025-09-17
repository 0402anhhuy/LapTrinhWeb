package com.ltw.bt06.controller;

import com.ltw.bt06.entity.User;
import com.ltw.bt06.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            model.addAttribute("error", "Tài khoản không tồn tại");
            return "login";
        }

        if (!password.equals(user.getPassword())) {
            model.addAttribute("error", "Sai mật khẩu");
            return "login";
        }

        session.setAttribute("AUTH_USER", user.getUsername());
        return "redirect:/list";
    }
}
