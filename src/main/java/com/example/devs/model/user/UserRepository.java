package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import com.example.devs.model.board.Board;
import com.example.devs.model.reply.Reply;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("select u from User u where u.email = :email and u.provider = 'EMAIL'")
    Optional<User> findByEmail(@Param("email") String email);

    // 이메일로 회원정보 조회
    @Query("select u from User u where u.email = :email and u.provider = :provider")
    Optional<User> findByEmailAndProvider(@Param("email") String email, @Param("provider") UserProvider provider);

    // 내가 작성한 게시판 조회
    @Query("SELECT b FROM Board b WHERE b.user.id = :id")
    Page<Board> findMyBoardsById(@Param("id") Integer id, Pageable pageable);

    // 내가 작성한 댓글 조회
    @Query("SELECT r FROM Reply r JOIN r.board b WHERE r.user.id = :id")
    Page<Reply> findMyRepliesById(@Param("id") Integer id, Pageable pageable);

    // 사용자 프로필 업데이트
    @Transactional
    @Modifying
    @Query("""
       UPDATE User u
       SET u.nickname = :nickname, u.position = :position, u.introduce = :introduce, u.image = :profileImg
       WHERE u.id = :id
        AND u.status = 'ACTIVE'
        AND u.role = 'USER'
       """)
    Integer updateProfileById(@Param("id") Integer id,
                              @Param("nickname")String nickname,
                              @Param("position")String position,
                              @Param("introduce")String introduce,
                              @Param("profileImg")String profileImg);
}