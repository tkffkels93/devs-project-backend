package com.example.devs.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email")
    User findByEmailV2(String email);

    @Query("select u from User u where u.email = :email and u.provider = 'EMAIL'")
    Optional<User> findByEmail(String email);
}