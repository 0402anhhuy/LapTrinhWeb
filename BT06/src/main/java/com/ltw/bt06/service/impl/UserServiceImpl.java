package com.ltw.bt06.service.impl;

import com.ltw.bt06.entity.User;
import com.ltw.bt06.repository.UserRepository;
import com.ltw.bt06.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
