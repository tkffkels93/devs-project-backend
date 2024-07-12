package com.example.devs.model.user;

import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import com.example.devs._core.errors.exception.Exception400;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception403;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // 로그인 (관리자)
    @Transactional
    public User adminLogin(UserRequest.AdminLoginDTO adminLoginDTO) {
        User user = userRepository.findByEmailAndUserRole(adminLoginDTO.getEmail(), UserRole.ADMIN)
                .orElseThrow(() -> new Exception400("존재하지 않는 관리자입니다."));

        if (!user.getPassword().equals(adminLoginDTO.getPassword())) {
            throw new Exception400("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    // 회원 정보 리스트 (관리자)
    public UserResponse.UserListDTO getUserList(User sessionAdmin) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        List<UserStatus> validStatuses = Arrays.asList(UserStatus.ACTIVE, UserStatus.BLOCKED);
        List<User> userDTO = userRepository.findByRoleAndStatusIn(UserRole.USER, validStatuses);
        Integer totalUserCount = userDTO.size();
        List<UserResponse.UserList> userList = userDTO.stream().map(UserResponse.UserList::new).toList();

        return new UserResponse.UserListDTO(totalUserCount, userList);
    }

    // 회원 상세 정보 (관리자)
    public UserResponse.DetailDTO getUserDetail(User sessionAdmin, Integer userId) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception400("존재하지 않는 회원입니다."));

        return new UserResponse.DetailDTO(user);
    }

    // 회원 삭제 (관리자)
    @Transactional
    public void deleteUser(User sessionAdmin, Integer userId) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception400("존재하지 않는 회원입니다."));

        if (user.getStatus() == UserStatus.DELETED) {
            throw new Exception400("이미 삭제된 회원입니다.");
        }

        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);
    }

    // 회원 차단 (관리자)
    @Transactional
    public void blockUser(User sessionAdmin, Integer userId) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception400("존재하지 않는 회원입니다."));

        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new Exception400("이미 차단된 회원입니다.");
        }

        user.setStatus(UserStatus.BLOCKED);
        userRepository.save(user);
    }
}
