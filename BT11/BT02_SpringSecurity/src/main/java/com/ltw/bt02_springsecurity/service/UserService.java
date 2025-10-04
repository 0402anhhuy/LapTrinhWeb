package com.ltw.bt02_springsecurity.service;

import com.ltw.bt02_springsecurity.entity.UserInfo;
import com.ltw.bt02_springsecurity.repository.UserInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "Success";
    }
}
