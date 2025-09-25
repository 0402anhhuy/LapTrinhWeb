package com.ltw.trananhhuy_23110229.repository;

import com.ltw.trananhhuy_23110229.entity.Share_23110229;
import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShareRepository_23110229 extends JpaRepository<Share_23110229, Integer> {

    @Query("SELECT s FROM Share_23110229 s WHERE s.video_23110229 = ?1")
    List<Share_23110229> findByVideo_23110229(Video_23110229 video_23110229);

    @Query("SELECT COUNT(s) FROM Share_23110229 s WHERE s.video_23110229.videoId_23110229 = ?1")
    Long countByVideoId_23110229(String videoId_23110229);
}