package com.dailymap.dailymap.api.token.service;

import com.dailymap.dailymap.api.token.dto.AccessTokenResponseDto;
import com.dailymap.dailymap.domain.jwt.dto.TokenDto;
import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.service.MemberService;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AccessTokenReissueService {

    private final MemberService memberService;

    private final TokenManager tokenManager;

    public AccessTokenResponseDto getAccessTokenResponseDto(String authorization) {
        String refreshToken = authorization.split(" ")[1];

        Member findMember = memberService.findMemberByRefreshToken(refreshToken)
            .orElseThrow(() -> new BusinessException(MEMBER_NOT_EXISTS_BY_REFRESH_TOKEN));
        String email = findMember.getEmail();
        Date refreshTokenExpireTime = Timestamp.valueOf(findMember.getTokenExpirationTime());

        if (tokenManager.isTokenExpired(refreshTokenExpireTime)) {
            throw new BusinessException(REFRESH_TOKEN_EXPIRED);
        }

        TokenDto tokenDto = tokenManager.createTokenDto(email);

        return AccessTokenResponseDto.of(tokenDto);
    }

}
