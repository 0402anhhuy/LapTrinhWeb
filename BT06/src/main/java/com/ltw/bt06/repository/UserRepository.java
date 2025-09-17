package com.ltw.bt06.repository;

import com.ltw.bt06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findAllBy();
    User findByUsername(String username);
}
