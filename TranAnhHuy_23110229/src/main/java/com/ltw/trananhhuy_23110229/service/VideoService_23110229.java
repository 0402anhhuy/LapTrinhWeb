package com.ltw.trananhhuy_23110229.service;

import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VideoService_23110229 {
    List<Video_23110229> findAll_23110229();
    Optional<Video_23110229> findById_23110229(String videoId_23110229);
    Video_23110229 save_23110229(Video_23110229 video_23110229);
    void deleteById_23110229(String videoId_23110229);
    Page<Video_23110229> findAll_23110229(Pageable pageable_23110229);
    List<Video_23110229> findByCategoryId_23110229(Integer categoryId_23110229);
}