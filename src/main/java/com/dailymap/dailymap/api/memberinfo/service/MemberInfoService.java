package com.dailymap.dailymap.api.memberinfo.service;

import com.dailymap.dailymap.api.memberinfo.dto.MemberInfoResponseDto;
import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.service.MemberService;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import com.dailymap.dailymap.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberService memberService;


    public MemberInfoResponseDto getMemberInfo(String authorization) {
        String refreshToken = authorization.split(" ")[1];

        Member findMember = memberService.findMemberByRefreshToken(refreshToken)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS_BY_REFRESH_TOKEN));

        return MemberInfoResponseDto.of(findMember);
    }
}
