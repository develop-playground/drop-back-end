package com.dailymap.dailymap.api.token.controller;

import com.dailymap.dailymap.api.token.dto.AccessTokenResponseDto;
import com.dailymap.dailymap.api.token.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "JWT 토큰", description = "JWT 토큰 API")
@SecurityRequirement(name = "Bearer Authentication")
public class TokenController {

    private final TokenService tokenService;

    @Operation(summary = "액세스 토큰 재발급", description = "리프레쉬 토큰을 통해서 액세스 토큰 재발급")
    @PostMapping("/token/reissue")
    public AccessTokenResponseDto accessTokenReissue(
        @Schema(hidden = true) @RequestHeader("Authorization")String authorization
    ) {
        AccessTokenResponseDto responseDto = tokenService.getAccessTokenResponseDto(authorization);
        return responseDto;
    }

    @Operation(summary = "리프레쉬 토큰 만료여부", description = "리프레쉬 토큰 만료 여부 확인")
    @GetMapping("/refreshtoken")
    public ResponseEntity<Boolean> refreshTokenExpiredOrNot(
        @Schema(hidden = true) @RequestHeader("Authorization") String authorization
    ) {
        boolean isExpired = tokenService.isRefreshTokenExpired(authorization);
        return ResponseEntity.ok(isExpired);
    }

}
