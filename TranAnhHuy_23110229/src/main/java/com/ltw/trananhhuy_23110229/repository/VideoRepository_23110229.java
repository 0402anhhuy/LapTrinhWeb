package com.ltw.trananhhuy_23110229.repository;

import com.ltw.trananhhuy_23110229.entity.Video_23110229;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository_23110229 extends JpaRepository<Video_23110229, String> {

    @Query("SELECT v FROM Video_23110229 v")
    Page<Video_23110229> findAllPaged_23110229(Pageable pageable_23110229);

    @Query("SELECT v FROM Video_23110229 v WHERE v.category_23110229.categoryId_23110229 = ?1")
    List<Video_23110229> findByCategory_23110229_CategoryId_23110229(Integer categoryId_23110229);

    @Query("SELECT v FROM Video_23110229 v WHERE v.active_23110229 = true")
    List<Video_23110229> findActiveVideos_23110229();

    @Query("SELECT v.category_23110229, COUNT(v) FROM Video_23110229 v GROUP BY v.category_23110229")
    List<Object[]> countVideosByCategory_23110229();
}