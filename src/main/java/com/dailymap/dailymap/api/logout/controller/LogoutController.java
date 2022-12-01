package com.dailymap.dailymap.api.logout.controller;


import com.dailymap.dailymap.api.logout.service.LogoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LogoutController {

    private final LogoutService logoutService;

    @Operation(summary = "로그아웃", description = "로그아웃을 통한 리프레쉬 토큰 만료 처리", tags = "카카오 로그인")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization")String authorization) {
        return ResponseEntity.ok(logoutService.logout(authorization));
    }

}
