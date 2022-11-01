package com.dailymap.dailymap.api.token.controller;

import com.dailymap.dailymap.api.token.dto.AccessTokenResponseDto;
import com.dailymap.dailymap.api.token.service.AccessTokenReissueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AccessTokenReissueController {

    private final AccessTokenReissueService accessTokenReissueService;

    @PostMapping("/token/reissue")
    public AccessTokenResponseDto accessTokenReissue(@RequestHeader("Authorization")String authorization) {
        AccessTokenResponseDto responseDto = accessTokenReissueService.getAccessTokenResponseDto(authorization);
        return responseDto;
    }

}
