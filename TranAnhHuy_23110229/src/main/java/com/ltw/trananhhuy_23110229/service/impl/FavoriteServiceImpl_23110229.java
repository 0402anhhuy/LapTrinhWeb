package com.ltw.trananhhuy_23110229.service.impl;

import com.ltw.trananhhuy_23110229.entity.Favorite_23110229;
import com.ltw.trananhhuy_23110229.entity.User_23110229;
import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import com.ltw.trananhhuy_23110229.repository.FavoriteRepository_23110229;
import com.ltw.trananhhuy_23110229.service.FavoriteService_23110229;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteServiceImpl_23110229 implements FavoriteService_23110229 {

    private final FavoriteRepository_23110229 favoriteRepository;

    @Override
    public List<Favorite_23110229> findAll_23110229() {
        return favoriteRepository.findAll();
    }

    @Override
    public Optional<Favorite_23110229> findById_23110229(Integer favoriteId_23110229) {
        return favoriteRepository.findById(favoriteId_23110229);
    }

    @Override
    @Transactional
    public Favorite_23110229 save_23110229(Favorite_23110229 favorite_23110229) {
        return favoriteRepository.save(favorite_23110229);
    }

    @Override
    @Transactional
    public void deleteById_23110229(Integer favoriteId_23110229) {
        favoriteRepository.deleteById(favoriteId_23110229);
    }

    @Override
    public List<Favorite_23110229> findByVideo_23110229(Video_23110229 video_23110229) {
        return favoriteRepository.findByVideo_23110229(video_23110229);
    }

    @Override
    public List<Favorite_23110229> findByUser_23110229(User_23110229 user_23110229) {
        return favoriteRepository.findByUser_23110229(user_23110229);
    }

    @Override
    public boolean existsByUser_23110229AndVideo_23110229(User_23110229 user_23110229, Video_23110229 video_23110229) {
        return favoriteRepository.existsByUser_23110229AndVideo_23110229(user_23110229, video_23110229);
    }

    @Override
    public Long countByVideoId_23110229(String videoId_23110229) {
        return favoriteRepository.countByVideoId_23110229(videoId_23110229);
    }
}