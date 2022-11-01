package com.dailymap.dailymap.api.logout.service;

import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.service.MemberService;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.MEMBER_NOT_EXISTS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LogoutService {

    private final TokenManager tokenManager;

    private final MemberService memberService;

    @Transactional
    public String logout(String authorization) {
        String refreshToken = authorization.split(" ")[1];

        Claims claims = tokenManager.getTokenClaims(refreshToken);
        String email = claims.getAudience();

        Member findMember = memberService.findMemberByEmail(email)
            .orElseThrow(() -> new BusinessException(MEMBER_NOT_EXISTS));

        findMember.expireRefreshToken();

        return "logout : success";
    }

}
