package com.ltw.trananhhuy_23110229.repository;

import com.ltw.trananhhuy_23110229.entity.User_23110229;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository_23110229 extends JpaRepository<User_23110229, String> {

    @Query("SELECT u FROM User_23110229 u WHERE u.username_23110229 = ?1 AND u.password_23110229 = ?2")
    User_23110229 findByUsernameAndPassword_23110229(String username_23110229, String password_23110229);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User_23110229 u WHERE u.username_23110229 = ?1")
    boolean existsByUsername_23110229(String username_23110229);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User_23110229 u WHERE u.email_23110229 = ?1")
    boolean existsByEmail_23110229(String email_23110229);
}