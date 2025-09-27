package com.ltw.bt09.repository;

import com.ltw.bt09.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    List<User> findAllBy();
    User findUserById(Long id);
}
