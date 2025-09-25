package com.ltw.trananhhuy_23110229.controller;

import com.ltw.trananhhuy_23110229.entity.User_23110229;
import com.ltw.trananhhuy_23110229.service.CategoryService_23110229;
import com.ltw.trananhhuy_23110229.service.VideoService_23110229;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController_23110229 {
    private final VideoService_23110229 videoService_23110229;
    private final CategoryService_23110229 categoryService_23110229;

    @GetMapping("/")
    public String home_23110229(Model model_23110229,
                                HttpSession session_23110229,
                                @RequestParam(defaultValue = "0") int page_23110229) {
        User_23110229 user_23110229 = (User_23110229) session_23110229.getAttribute("user_23110229");
        if (user_23110229 == null) {
            return "redirect:/auth/login";
        }

        int size_23110229 = 6;
        PageRequest pageable_23110229 = PageRequest.of(page_23110229, size_23110229);
        Page<com.ltw.trananhhuy_23110229.entity.Video_23110229> videosPage_23110229 =
                videoService_23110229.findAll_23110229(pageable_23110229);

        model_23110229.addAttribute("categories_23110229", categoryService_23110229.findAll_23110229());
        model_23110229.addAttribute("videosPage_23110229", videosPage_23110229);
        model_23110229.addAttribute("currentPage_23110229", page_23110229);
        model_23110229.addAttribute("totalPages_23110229", videosPage_23110229.getTotalPages());

        return "user/home";
    }
}