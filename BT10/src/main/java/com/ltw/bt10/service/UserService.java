package com.ltw.bt10.service;

import com.ltw.bt10.dto.UserDTO;
import com.ltw.bt10.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(UserDTO userDTO);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
    List<User> findAll();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User convertToEntity(UserDTO userDTO);
    UserDTO convertToDTO(User user);
}
