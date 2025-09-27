package com.ltw.bt09.service;

import com.ltw.bt09.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllBy();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
}
