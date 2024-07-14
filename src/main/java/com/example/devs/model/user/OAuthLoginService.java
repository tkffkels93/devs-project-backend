package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import org.springframework.stereotype.Service;

@Service
public interface OAuthLoginService<T> {
    UserProvider getProvider();
    UserResponse.OAuthTokenDTO getAccessToken(String authorizationCode);
    T getUserInfo(String accessToken);
}
