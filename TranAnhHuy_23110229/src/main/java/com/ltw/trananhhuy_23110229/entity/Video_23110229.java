package com.ltw.trananhhuy_23110229.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video_23110229 {
    @Id
    @Column(name = "VideoId", length = 50)
    private String videoId_23110229;

    @Column(name = "Title", length = 200, nullable = false)
    private String title_23110229;

    @Column(name = "Poster", length = 50)
    private String poster_23110229;

    @Column(name = "Views")
    private Integer views_23110229 = 0;

    @Column(name = "Description", length = 500)
    private String description_23110229;

    @Column(name = "Active")
    private Boolean active_23110229 = true;

    @OneToMany(mappedBy = "video_23110229")
    private java.util.List<Favorite_23110229> favorite_23110229;

    @OneToMany(mappedBy = "video_23110229")
    private java.util.List<Share_23110229> share_23110229;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category_23110229 category_23110229;
}
