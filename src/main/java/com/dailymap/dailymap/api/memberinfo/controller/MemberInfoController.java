package com.dailymap.dailymap.api.memberinfo.controller;

import com.dailymap.dailymap.api.memberinfo.dto.MemberInfoResponseDto;
import com.dailymap.dailymap.api.memberinfo.dto.UpdateUsernameRequestDto;
import com.dailymap.dailymap.api.memberinfo.service.MemberInfoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/user")
    public ResponseEntity<MemberInfoResponseDto> updateMemberUsername(
        @RequestHeader("Authorization")String authorization,
        @RequestBody UpdateUsernameRequestDto requestDto
        ) {
        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.updateUsername(authorization,requestDto);
        return ResponseEntity.ok(memberInfoResponseDto);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> memberSecession(@RequestHeader("Authorization")String authorization) {
        return ResponseEntity.ok(memberInfoService.memberSecession(authorization));
    }

}
