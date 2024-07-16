package com.example.devs.model.user;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AccessTokenStorage {
    private final Map<String, String> tokenStorage = new HashMap<>();

    public void saveToken(String providerId, String accessToken) {
        tokenStorage.put(providerId, accessToken);
    }

    public String getToken(String providerId) {
        return tokenStorage.get(providerId);
    }

    public void deleteToken(String providerId) {
        tokenStorage.remove(providerId);
    }

    // 모든 저장된 토큰을 반환하는 메서드
    public Map<String, String> getAllTokens() {
        return new HashMap<>(tokenStorage);
    }

    // 저장된 모든 토큰을 출력하는 메서드
    public void printAllTokens() {
        String allTokens = tokenStorage.entrySet().stream()
                .map(entry -> "ProviderId: " + entry.getKey() + ", AccessToken: " + entry.getValue())
                .collect(Collectors.joining("\n"));
        System.out.println(allTokens);
    }
}
