package com.ltw.trananhhuy_23110229.service.impl;

import com.ltw.trananhhuy_23110229.entity.User_23110229;
import com.ltw.trananhhuy_23110229.repository.UserRepository_23110229;
import com.ltw.trananhhuy_23110229.service.UserService_23110229;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl_23110229 implements UserService_23110229 {

    private final UserRepository_23110229 userRepository;

    @Override
    public List<User_23110229> findAll_23110229() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User_23110229> findById_23110229(String username_23110229) {
        return userRepository.findById(username_23110229);
    }

    @Override
    @Transactional
    public User_23110229 save_23110229(User_23110229 user_23110229) {
        return userRepository.save(user_23110229);
    }

    @Override
    @Transactional
    public void deleteById_23110229(String username_23110229) {
        userRepository.deleteById(username_23110229);
    }
}