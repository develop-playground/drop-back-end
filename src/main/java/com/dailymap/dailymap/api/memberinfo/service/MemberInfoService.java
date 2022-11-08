package com.dailymap.dailymap.api.memberinfo.service;

import com.dailymap.dailymap.api.memberinfo.dto.MemberInfoResponseDto;
import com.dailymap.dailymap.api.memberinfo.dto.UpdateUsernameRequestDto;
import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.service.MemberService;
import com.dailymap.dailymap.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.MEMBER_NOT_EXISTS_BY_REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberInfoService {

    private final MemberService memberService;

    public MemberInfoResponseDto getMemberInfo(String authorization) {
        String refreshToken = authorization.split(" ")[1];

        Member findMember = getMemberByRefreshToken(refreshToken);

        return MemberInfoResponseDto.of(findMember);
    }

    @Transactional
    public MemberInfoResponseDto updateUsername(String authorization, UpdateUsernameRequestDto requestDto) {
        String refreshToken = authorization.split(" ")[1];

        Member findMember = getMemberByRefreshToken(refreshToken);

        String newUsername = requestDto.getUsername();
        findMember.updateUsername(newUsername);

        return MemberInfoResponseDto.of(findMember);
    }

    @Transactional
    public String memberSecession(String authorization) {
        String refreshToken = authorization.split(" ")[1];

        Member findMember = getMemberByRefreshToken(refreshToken);

        memberService.delete(findMember);
        return "secession : success";
    }

    private Member getMemberByRefreshToken(String refreshToken) {
        return memberService.findMemberByRefreshToken(refreshToken)
            .orElseThrow(() -> new BusinessException(MEMBER_NOT_EXISTS_BY_REFRESH_TOKEN));
    }

}
