package com.ltw.trananhhuy_23110229.service;

import com.ltw.trananhhuy_23110229.entity.User_23110229;

import java.util.List;
import java.util.Optional;

public interface UserService_23110229 {
    List<User_23110229> findAll_23110229();

    Optional<User_23110229> findById_23110229(String username_23110229);

    User_23110229 save_23110229(User_23110229 user_23110229);

    void deleteById_23110229(String username_23110229);
}