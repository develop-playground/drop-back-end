package com.dailymap.dailymap.api.login.controller;

import com.dailymap.dailymap.api.login.dto.KakaoTokenResponseDto;
import com.dailymap.dailymap.api.login.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;


    @GetMapping("/kakao/callback")
    public ResponseEntity<KakaoTokenResponseDto> loginCallback(String code) {
        KakaoTokenResponseDto kakaoToken = kakaoLoginService.getKakaoTokenDto(code, clientId, clientSecret);
        return ResponseEntity.ok(kakaoToken);
    }

}
