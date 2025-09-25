package com.ltw.trananhhuy_23110229.service.impl;

import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import com.ltw.trananhhuy_23110229.repository.VideoRepository_23110229;
import com.ltw.trananhhuy_23110229.service.VideoService_23110229;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoServiceImpl_23110229 implements VideoService_23110229 {

    private final VideoRepository_23110229 videoRepository;

    @Override
    public List<Video_23110229> findAll_23110229() {
        return videoRepository.findAll();
    }

    @Override
    public Optional<Video_23110229> findById_23110229(String videoId_23110229) {
        return videoRepository.findById(videoId_23110229);
    }

    @Override
    @Transactional
    public Video_23110229 save_23110229(Video_23110229 video_23110229) {
        return videoRepository.save(video_23110229);
    }

    @Override
    @Transactional
    public void deleteById_23110229(String videoId_23110229) {
        videoRepository.deleteById(videoId_23110229);
    }

    @Override
    public Page<Video_23110229> findAll_23110229(Pageable pageable_23110229) {
        return videoRepository.findAllPaged_23110229(pageable_23110229);
    }

    @Override
    public List<Video_23110229> findByCategoryId_23110229(Integer categoryId_23110229) {
        return videoRepository.findByCategory_23110229_CategoryId_23110229(categoryId_23110229);
    }
}
