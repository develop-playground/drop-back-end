package com.dailymap.dailymap.api.login.controller;

import com.dailymap.dailymap.api.login.dto.KakaoTokenResponseDto;
import com.dailymap.dailymap.api.login.dto.LoginRequestDto;
import com.dailymap.dailymap.api.login.service.KakaoLoginService;
import com.dailymap.dailymap.domain.jwt.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.client.redirect-uri}")
    private String redirectUri;

    @GetMapping("/kakao/callback")
    public ResponseEntity<KakaoTokenResponseDto> loginCallback(String code) {
        KakaoTokenResponseDto kakaoToken = kakaoLoginService.getKakaoTokenDto(
            code,
            clientId,
            clientSecret,
            redirectUri
        );

        return ResponseEntity.ok(kakaoToken);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestHeader("Authorization") String authorization, @RequestBody LoginRequestDto loginRequestDto) {
        TokenDto tokenDto = kakaoLoginService.login(authorization, loginRequestDto);

        return ResponseEntity.ok(tokenDto);
    }

}
