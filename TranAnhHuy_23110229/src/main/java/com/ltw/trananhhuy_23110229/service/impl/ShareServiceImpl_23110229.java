package com.ltw.trananhhuy_23110229.service.impl;

import com.ltw.trananhhuy_23110229.entity.Share_23110229;
import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import com.ltw.trananhhuy_23110229.repository.ShareRepository_23110229;
import com.ltw.trananhhuy_23110229.service.ShareService_23110229;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShareServiceImpl_23110229 implements ShareService_23110229 {

    private final ShareRepository_23110229 shareRepository;

    @Override
    public List<Share_23110229> findAll_23110229() {
        return shareRepository.findAll();
    }

    @Override
    public Optional<Share_23110229> findById_23110229(Integer shareId_23110229) {
        return shareRepository.findById(shareId_23110229);
    }

    @Override
    @Transactional
    public Share_23110229 save_23110229(Share_23110229 share_23110229) {
        return shareRepository.save(share_23110229);
    }

    @Override
    @Transactional
    public void deleteById_23110229(Integer shareId_23110229) {
        shareRepository.deleteById(shareId_23110229);
    }

    @Override
    public List<Share_23110229> findByVideo_23110229(Video_23110229 video_23110229) {
        return shareRepository.findByVideo_23110229(video_23110229);
    }

    @Override
    public Long countByVideoId_23110229(String videoId_23110229) {
        return shareRepository.countByVideoId_23110229(videoId_23110229);
    }
}