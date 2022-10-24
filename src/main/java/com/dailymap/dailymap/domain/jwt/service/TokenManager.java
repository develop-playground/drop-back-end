package com.dailymap.dailymap.domain.jwt.service;

import com.dailymap.dailymap.domain.jwt.dto.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {

    @Value("${token.access-token-expired-time}")
    private String accessTokenExpiredTime;

    @Value("${token.refresh-token-expired-time}")
    private String refreshTokenExpiredTime;

    @Value("${token.secret}")
    private String tokenSecret;

    public TokenDto createTokenDto(String email) {

        return null;
    }



}
