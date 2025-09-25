package com.ltw.trananhhuy_23110229.repository;

import com.ltw.trananhhuy_23110229.entity.Favorite_23110229;
import com.ltw.trananhhuy_23110229.entity.User_23110229;
import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository_23110229 extends JpaRepository<Favorite_23110229, Integer> {

    @Query("SELECT f FROM Favorite_23110229 f WHERE f.video_23110229 = ?1")
    List<Favorite_23110229> findByVideo_23110229(Video_23110229 video_23110229);

    @Query("SELECT f FROM Favorite_23110229 f WHERE f.user_23110229 = ?1")
    List<Favorite_23110229> findByUser_23110229(User_23110229 user_23110229);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Favorite_23110229 f WHERE f.user_23110229 = ?1 AND f.video_23110229 = ?2")
    boolean existsByUser_23110229AndVideo_23110229(User_23110229 user_23110229, Video_23110229 video_23110229);

    @Query("SELECT COUNT(f) FROM Favorite_23110229 f WHERE f.video_23110229.videoId_23110229 = ?1")
    Long countByVideoId_23110229(String videoId_23110229);
}