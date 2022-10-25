package com.dailymap.dailymap.api.login.service;

import com.dailymap.dailymap.api.login.client.KakaoTokenFeignClient;
import com.dailymap.dailymap.api.login.dto.KakaoTokenRequestDto;
import com.dailymap.dailymap.api.login.dto.KakaoTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoTokenFeignClient kakaoTokenFeignClient;

    public KakaoTokenResponseDto getKakaoTokenDto(String code, String clientId,
                                                  String clientSecret, String redirectUri) {
        KakaoTokenRequestDto kakaoTokenRequestDto = KakaoTokenRequestDto.of(
            code,
            clientId,
            clientSecret,
            redirectUri
        );

        return kakaoTokenFeignClient.getKakaoToken(kakaoTokenRequestDto);
    }

}
