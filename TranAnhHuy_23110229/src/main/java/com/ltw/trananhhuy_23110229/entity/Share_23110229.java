package com.ltw.trananhhuy_23110229.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Shares")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Share_23110229 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShareId")
    private Integer shareId_23110229;

    @Column(name = "Emails", length = 50)
    private String emails_23110229;

    @Column(name = "SharedDate")
    @Temporal(TemporalType.DATE)
    private Date sharedDate_23110229;

    @ManyToOne
    @JoinColumn(name = "Username")
    private User_23110229 user_23110229;

    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video_23110229 video_23110229;
}
