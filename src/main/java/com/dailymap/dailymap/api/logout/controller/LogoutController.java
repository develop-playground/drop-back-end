package com.dailymap.dailymap.api.logout.controller;


import com.dailymap.dailymap.api.logout.service.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization")String authorization) {
        return logoutService.logout(authorization);
    }

}
