package com.dailymap.dailymap.api.login.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Builder
public class KakaoTokenRequestDto {

    private String grant_type;

    private String client_id;

    private String redirect_uri;

    private String code;

    private String client_secret;

    public static KakaoTokenRequestDto of(String code, String clientId,
                                          String clientSecret, String redirectUri) {
        return KakaoTokenRequestDto.builder()
            .grant_type("authorization_code")
            .client_id(clientId)
            .redirect_uri(redirectUri)
            .code(code)
            .client_secret(clientSecret)
            .build();
    }

}
