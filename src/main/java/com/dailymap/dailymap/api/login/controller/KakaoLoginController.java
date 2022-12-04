package com.dailymap.dailymap.api.login.controller;

import com.dailymap.dailymap.api.login.dto.KakaoTokenResponseDto;
import com.dailymap.dailymap.api.login.dto.LoginRequestDto;
import com.dailymap.dailymap.api.login.service.KakaoLoginService;
import com.dailymap.dailymap.domain.jwt.dto.TokenDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import lombok.RequiredArgsConstructor;

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

    @Operation(summary = "카카오 콜백", description = "카카오 측에서 호출하는 콜백 메서드", tags = "카카오 로그인", hidden = true)
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

    @Operation(summary = "로그인", description = "카카오에서 제공하는 액세스 토큰을 이용한 로그인", tags = "카카오 로그인")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(
        @Schema(hidden = true) @RequestHeader("Authorization") String authorization,
        @RequestBody LoginRequestDto loginRequestDto
    ) {
        TokenDto tokenDto = kakaoLoginService.login(authorization, loginRequestDto);

        return ResponseEntity.ok(tokenDto);
    }

}
