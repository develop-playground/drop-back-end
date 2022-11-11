package com.dailymap.dailymap.api.token.controller;

import com.dailymap.dailymap.api.token.dto.AccessTokenResponseDto;
import com.dailymap.dailymap.api.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/token/reissue")
    public AccessTokenResponseDto accessTokenReissue(@RequestHeader("Authorization")String authorization) {
        AccessTokenResponseDto responseDto = tokenService.getAccessTokenResponseDto(authorization);
        return responseDto;
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<Boolean> refreshTokenExpiredOrNot(@RequestHeader("Authorization") String authorization) {
        boolean isExpired = tokenService.isRefreshTokenExpired(authorization);
        return ResponseEntity.ok(isExpired);
    }

}
