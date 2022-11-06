package com.dailymap.dailymap.api.memberinfo.controller;

import com.dailymap.dailymap.api.memberinfo.dto.MemberInfoResponseDto;
import com.dailymap.dailymap.api.memberinfo.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    @GetMapping("/user")
    public ResponseEntity<MemberInfoResponseDto> findMemberInfo(@RequestHeader("Authorization") String authorization) {
        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.getMemberInfo(authorization);
        return ResponseEntity.ok(memberInfoResponseDto);
    }

}
