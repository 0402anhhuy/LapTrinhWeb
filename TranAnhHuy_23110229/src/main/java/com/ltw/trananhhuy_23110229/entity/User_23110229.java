package com.ltw.trananhhuy_23110229.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User_23110229 {
    @Id
    @Column(name = "Username", length = 50)
    private String username_23110229;

    @Column(name = "Password", length = 50, nullable = false)
    private String password_23110229;

    @Column(name = "Phone", length = 15)
    private String phone_23110229;

    @Column(name = "Fullname", length = 50)
    private String fullname_23110229;

    @Column(name = "Email", length = 150)
    private String email_23110229;

    @Column(name = "Admin")
    private Boolean admin_23110229 = false;

    @Column(name = "Active")
    private Boolean active_23110229 = true;

    @Column(name = "Images", length = 500)
    private String images_23110229;

    @OneToMany(mappedBy = "user_23110229")
    private java.util.List<Favorite_23110229> favorite_23110229;

    @OneToMany(mappedBy = "user_23110229")
    private java.util.List<Share_23110229> share_23110229;
}
