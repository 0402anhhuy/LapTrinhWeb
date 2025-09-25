package com.ltw.trananhhuy_23110229.controller;

import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import com.ltw.trananhhuy_23110229.service.CategoryService_23110229;
import com.ltw.trananhhuy_23110229.service.VideoService_23110229;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/videos")
@RequiredArgsConstructor
public class VideoController_23110229 {

    private final VideoService_23110229 videoService_23110229;
    private final CategoryService_23110229 categoryService_23110229;

    @GetMapping
    public String listVideos_23110229(@RequestParam(defaultValue = "0") int page_23110229,
                                      Model model_23110229) {
        Page<Video_23110229> videoPage_23110229 = videoService_23110229.findAll_23110229(PageRequest.of(page_23110229, 6));
        model_23110229.addAttribute("videoPage_23110229", videoPage_23110229);
        model_23110229.addAttribute("currentPage_23110229", page_23110229);
        return "admin/video-list";
    }

    @GetMapping("/create")
    public String showCreateForm_23110229(Model model_23110229) {
        model_23110229.addAttribute("video_23110229", new Video_23110229());
        model_23110229.addAttribute("categories_23110229", categoryService_23110229.findAll_23110229());
        return "admin/video-form";
    }

    @PostMapping("/save")
    public String saveVideo_23110229(@ModelAttribute Video_23110229 video_23110229) {
        videoService_23110229.save_23110229(video_23110229);
        return "redirect:/admin/videos";
    }

    @GetMapping("/edit/{id_23110229}")
    public String showEditForm_23110229(@PathVariable String id_23110229, Model model_23110229) {
        Video_23110229 video_23110229 = videoService_23110229.findById_23110229(id_23110229).orElse(null);
        if (video_23110229 == null) {
            return "redirect:/admin/videos";
        }
        model_23110229.addAttribute("video_23110229", video_23110229);
        model_23110229.addAttribute("categories_23110229", categoryService_23110229.findAll_23110229());
        return "admin/video-form";
    }

    @GetMapping("/delete/{id_23110229}")
    public String deleteVideo_23110229(@PathVariable String id_23110229) {
        videoService_23110229.deleteById_23110229(id_23110229);
        return "redirect:/admin/videos";
    }
}