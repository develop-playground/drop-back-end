package com.dailymap.dailymap.api.token.dto;

import com.dailymap.dailymap.domain.jwt.dto.TokenDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class AccessTokenResponseDto {

    private String grantType;

    private String accessToken;

    private Date accessTokenExpireTime;

    public static AccessTokenResponseDto of(TokenDto tokenDto) {
        return AccessTokenResponseDto.builder()
            .grantType("Bearer")
            .accessToken(tokenDto.getAccessToken())
            .accessTokenExpireTime(tokenDto.getAccessTokenExpireTime())
            .build();
    }

}
