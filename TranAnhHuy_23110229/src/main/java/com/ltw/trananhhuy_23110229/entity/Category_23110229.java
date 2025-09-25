package com.ltw.trananhhuy_23110229.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category_23110229 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private Integer categoryId_23110229;

    @Column(name = "Categoryname", length = 100, nullable = false)
    private String categoryname_23110229;

    @Column(name = "Categorycode", length = 100)
    private String categorycode_23110229;

    @Column(name = "Images", length = 500)
    private String images_23110229;

    @Column(name = "Status")
    private Boolean status_23110229 = true;

    @OneToMany(mappedBy = "category_23110229")
    private java.util.List<Video_23110229> video_23110229;
}
