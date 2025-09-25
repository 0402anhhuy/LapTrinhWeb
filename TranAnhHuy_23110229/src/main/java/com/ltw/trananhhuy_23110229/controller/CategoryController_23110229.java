package com.ltw.trananhhuy_23110229.controller;

import com.ltw.trananhhuy_23110229.entity.Category_23110229;
import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import com.ltw.trananhhuy_23110229.service.CategoryService_23110229;
import com.ltw.trananhhuy_23110229.service.FavoriteService_23110229;
import com.ltw.trananhhuy_23110229.service.ShareService_23110229;
import com.ltw.trananhhuy_23110229.service.VideoService_23110229;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController_23110229 {

    private final CategoryService_23110229 categoryService_23110229;
    private final VideoService_23110229 videoService_23110229;
    private final FavoriteService_23110229 favoriteService_23110229;
    private final ShareService_23110229 shareService_23110229;

    @GetMapping
    public String listCategories_23110229(Model model_23110229) {
        List<Category_23110229> categories_23110229 = categoryService_23110229.findAll_23110229();
        model_23110229.addAttribute("categories_23110229", categories_23110229);

        Map<Integer, Long> videoCountByCategory_23110229 = categories_23110229.stream()
                .collect(Collectors.toMap(
                        Category_23110229::getCategoryId_23110229,
                        c -> (long) videoService_23110229.findByCategoryId_23110229(c.getCategoryId_23110229()).size()
                ));
        model_23110229.addAttribute("videoCountByCategory_23110229", videoCountByCategory_23110229);

        return "user/category-list";
    }

    @GetMapping("/{id_23110229}")
    public String videosByCategory_23110229(@PathVariable Integer id_23110229, Model model_23110229) {
        List<Video_23110229> videos_23110229 = videoService_23110229.findByCategoryId_23110229(id_23110229);
        model_23110229.addAttribute("videos_23110229", videos_23110229);

        Category_23110229 currentCategory_23110229 = categoryService_23110229.findAll_23110229().stream()
                .filter(c -> c.getCategoryId_23110229().equals(id_23110229))
                .findFirst().orElse(null);
        model_23110229.addAttribute("category_23110229", currentCategory_23110229);

        Map<String, Long> likeCounts_23110229 = new HashMap<>();
        Map<String, Long> shareCounts_23110229 = new HashMap<>();
        for (Video_23110229 v : videos_23110229) {
            likeCounts_23110229.put(v.getVideoId_23110229(), favoriteService_23110229.countByVideoId_23110229(v.getVideoId_23110229()));
            shareCounts_23110229.put(v.getVideoId_23110229(), shareService_23110229.countByVideoId_23110229(v.getVideoId_23110229()));
        }
        model_23110229.addAttribute("likeCounts_23110229", likeCounts_23110229);
        model_23110229.addAttribute("shareCounts_23110229", shareCounts_23110229);

        return "user/video-by-category";
    }
}