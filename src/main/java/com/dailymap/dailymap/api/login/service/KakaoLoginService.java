package com.dailymap.dailymap.api.login.service;

import com.dailymap.dailymap.api.login.client.KakaoTokenFeignClient;
import com.dailymap.dailymap.api.login.client.LoginFeignClient;
import com.dailymap.dailymap.api.login.dto.KakaoTokenRequestDto;
import com.dailymap.dailymap.api.login.dto.KakaoTokenResponseDto;
import com.dailymap.dailymap.api.login.dto.KakaoUserInfo;
import com.dailymap.dailymap.api.login.dto.LoginRequestDto;
import com.dailymap.dailymap.domain.jwt.dto.TokenDto;
import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import com.dailymap.dailymap.domain.member.constant.MemberType;
import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.service.MemberService;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import com.dailymap.dailymap.global.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static com.dailymap.dailymap.domain.member.constant.MemberType.KAKAO;
import static com.dailymap.dailymap.global.error.exception.ErrorCode.INVALID_MEMBER_TYPE;
import static com.dailymap.dailymap.global.error.exception.ErrorCode.MEMBER_NOT_EXISTS;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class KakaoLoginService {

    private final KakaoTokenFeignClient kakaoTokenFeignClient;

    private final LoginFeignClient loginFeignClient;

    private final TokenManager tokenManager;

    private final MemberService memberService;

    public KakaoTokenResponseDto getKakaoTokenDto(
        String code,
        String clientId,
        String clientSecret,
        String redirectUri
    ) {
        KakaoTokenRequestDto kakaoTokenRequestDto = KakaoTokenRequestDto.of(
            code,
            clientId,
            clientSecret,
            redirectUri
        );

        return kakaoTokenFeignClient.getKakaoToken(kakaoTokenRequestDto);
    }

    @Transactional
    public TokenDto login(String authorization, LoginRequestDto loginRequestDto) throws BusinessException{
        MemberType memberType = loginRequestDto.getMemberType();
        if (!KAKAO.equals(memberType)) {
            throw new BusinessException(INVALID_MEMBER_TYPE);
        }

        KakaoUserInfo userInfo = loginFeignClient.getKakaoUserInfo(authorization);
        String email = userInfo.getKakaoAccount().getEmail();
        String username = userInfo.getProperties().get("nickname");

        Optional<Member> findMember = memberService.findMemberByEmail(email);
        if (findMember.isPresent()) {
            return updateRefreshInfo(findMember.orElseThrow(() -> new BusinessException(MEMBER_NOT_EXISTS)));
        }

        return register(email, username, memberType);
    }

    private TokenDto register(String email, String name, MemberType memberType) {
        Member member = Member.of(email, name, memberType);
        TokenDto tokenDto = tokenManager.createTokenDto(email);

        updateMemberRefreshToken(member, tokenDto);
        memberService.register(member);

        return tokenDto;
    }

    private TokenDto updateRefreshInfo(Member member) {
        TokenDto tokenDto = tokenManager.createTokenDto(member.getEmail());
        updateMemberRefreshToken(member, tokenDto);

        return tokenDto;
    }

    private void updateMemberRefreshToken(Member member, TokenDto tokenDto) {
        String refreshToken = tokenDto.getRefreshToken();
        Date expireTime = tokenDto.getRefreshTokenExpireTime();
        LocalDateTime refreshTokenExpireTime = DateTimeUtils.convertToLocalDateTime(expireTime);

        member.updateRefreshInfo(refreshToken, refreshTokenExpireTime);
    }

}
