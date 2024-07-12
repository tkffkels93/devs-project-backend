package com.example.devs.model.user;

import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 관리자 계정 찾기
    @Query("select u from User u where u.email = :email and u.role = :role")
    Optional<User> findByEmailAndUserRole(@Param("email") String email, @Param("role") UserRole role);

    // 회원 정보 리스트 찾기
    @Query("select u from User u where u.role = :role and u.status in :status order by u.id desc")
    List<User> findByRoleAndStatusIn(@Param("role") UserRole role, @Param("status") List<UserStatus> status);

    @Query("select u from User u where u.email = :email")
    User findByEmailV2(String email);

    @Query("select u from User u where u.email = :email and u.provider = 'EMAIL'")
    Optional<User> findByEmail(String email);
}