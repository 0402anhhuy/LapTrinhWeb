package com.ltw.trananhhuy_23110229.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Favorites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite_23110229 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteId")
    private Integer favoriteId_23110229;

    @Column(name = "LikedDate")
    @Temporal(TemporalType.DATE)
    private Date likedDate_23110229;

    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video_23110229 video_23110229;

    @ManyToOne
    @JoinColumn(name = "Username")
    private User_23110229 user_23110229;
}
