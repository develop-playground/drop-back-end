package com.dailymap.dailymap.api.memberinfo.controller;

import com.dailymap.dailymap.api.memberinfo.dto.MemberInfoResponseDto;
import com.dailymap.dailymap.api.memberinfo.dto.UpdateUsernameRequestDto;
import com.dailymap.dailymap.api.memberinfo.service.MemberInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "회원 정보", description = "회원정보 API")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    @Operation(summary = "회원정보 조회", description = "사용자의 닉네임, 이메일 등을 조회하는 API")
    @GetMapping("/user")
    public ResponseEntity<MemberInfoResponseDto> findMemberInfo(@RequestHeader("Authorization") String authorization) {
        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.getMemberInfo(authorization);
        return ResponseEntity.ok(memberInfoResponseDto);
    }

    @Operation(summary = "회원정보 변경", description = "회원의 닉네임을 변경하는 API", hidden = true)
    @PatchMapping("/user")
    public ResponseEntity<MemberInfoResponseDto> updateMemberUsername(
        @RequestHeader("Authorization")String authorization,
        @RequestBody UpdateUsernameRequestDto requestDto
        ) {
        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.updateUsername(authorization,requestDto);
        return ResponseEntity.ok(memberInfoResponseDto);
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탍퇴를 위한 API")
    @DeleteMapping("/user")
    public ResponseEntity<String> memberSecession(@RequestHeader("Authorization")String authorization) {
        return ResponseEntity.ok(memberInfoService.memberSecession(authorization));
    }

}
