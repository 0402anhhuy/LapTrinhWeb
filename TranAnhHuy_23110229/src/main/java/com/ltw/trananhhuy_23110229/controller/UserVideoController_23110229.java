package com.ltw.trananhhuy_23110229.controller;

import com.ltw.trananhhuy_23110229.entity.Favorite_23110229;
import com.ltw.trananhhuy_23110229.entity.Share_23110229;
import com.ltw.trananhhuy_23110229.entity.User_23110229;
import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import com.ltw.trananhhuy_23110229.service.FavoriteService_23110229;
import com.ltw.trananhhuy_23110229.service.ShareService_23110229;
import com.ltw.trananhhuy_23110229.service.VideoService_23110229;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

// ... existing code ...
@Controller
@RequestMapping("/video")
@RequiredArgsConstructor
public class UserVideoController_23110229 {

    private final VideoService_23110229 videoService_23110229;
    private final FavoriteService_23110229 favoriteService_23110229;
    private final ShareService_23110229 shareService_23110229;

    @GetMapping("/{id_23110229}")
    public String videoDetail_23110229(@PathVariable String id_23110229,
                                       Model model_23110229,
                                       HttpSession session_23110229) {
        Video_23110229 video_23110229 = videoService_23110229.findById_23110229(id_23110229).orElse(null);
        if (video_23110229 == null) {
            return "redirect:/";
        }

        // tăng view
        Integer views = video_23110229.getViews_23110229() == null ? 0 : video_23110229.getViews_23110229();
        video_23110229.setViews_23110229(views + 1);
        videoService_23110229.save_23110229(video_23110229);

        Long likeCount_23110229 = favoriteService_23110229.countByVideoId_23110229(id_23110229);
        Long shareCount_23110229 = shareService_23110229.countByVideoId_23110229(id_23110229);

        model_23110229.addAttribute("video_23110229", video_23110229);
        model_23110229.addAttribute("likeCount_23110229", likeCount_23110229);
        model_23110229.addAttribute("shareCount_23110229", shareCount_23110229);

        return "user/video-detail";
    }

    @PostMapping("/{id_23110229}/like")
    public String likeVideo_23110229(@PathVariable String id_23110229, HttpSession session_23110229) {
        User_23110229 user_23110229 = (User_23110229) session_23110229.getAttribute("user_23110229");
        Video_23110229 video_23110229 = videoService_23110229.findById_23110229(id_23110229).orElse(null);
        if (user_23110229 != null && video_23110229 != null) {
            boolean exists = favoriteService_23110229.existsByUser_23110229AndVideo_23110229(user_23110229, video_23110229);
            if (!exists) {
                Favorite_23110229 f = new Favorite_23110229();
                f.setUser_23110229(user_23110229);
                f.setVideo_23110229(video_23110229);
                f.setLikedDate_23110229(new Date());
                favoriteService_23110229.save_23110229(f);
            }
        }
        return "redirect:/video/" + id_23110229;
    }

    @PostMapping("/{id_23110229}/share")
    public String shareVideo_23110229(@PathVariable String id_23110229,
                                      @RequestParam String emails_23110229,
                                      HttpSession session_23110229) {
        User_23110229 user_23110229 = (User_23110229) session_23110229.getAttribute("user_23110229");
        Video_23110229 video_23110229 = videoService_23110229.findById_23110229(id_23110229).orElse(null);
        if (user_23110229 != null && video_23110229 != null) {
            Share_23110229 s = new Share_23110229();
            s.setEmails_23110229(emails_23110229);
            s.setSharedDate_23110229(new Date());
            s.setUser_23110229(user_23110229);
            s.setVideo_23110229(video_23110229);
            shareService_23110229.save_23110229(s);
        }
        return "redirect:/video/" + id_23110229;
    }
}