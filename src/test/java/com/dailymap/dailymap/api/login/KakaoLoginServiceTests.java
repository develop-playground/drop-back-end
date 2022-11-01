package com.dailymap.dailymap.api.login;

import com.dailymap.dailymap.api.login.client.KakaoTokenFeignClient;
import com.dailymap.dailymap.api.login.client.LoginFeignClient;
import com.dailymap.dailymap.api.login.dto.KakaoTokenRequestDto;
import com.dailymap.dailymap.api.login.service.KakaoLoginService;
import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import com.dailymap.dailymap.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class KakaoLoginServiceTests {

    @Mock
    KakaoTokenFeignClient kakaoTokenFeignClient;

    @Mock
    LoginFeignClient loginFeignClient;

    @Mock
    TokenManager tokenManager;

    @Mock
    MemberService memberService;

    @Test
    @DisplayName("FeignClient를 이용한 카카오 API 호출 검증")
    public void kakaoAPICallVerificationTest() {
        // Arrange
        KakaoLoginService sut = new KakaoLoginService(
            kakaoTokenFeignClient,
            loginFeignClient,
            tokenManager,
            memberService
        );

        // Act
        sut.getKakaoTokenDto(
            "code",
            "clientId",
            "clientSecret",
            "redirectUri"
        );

        // Assert
        verify(kakaoTokenFeignClient, times(1))
            .getKakaoToken(any(KakaoTokenRequestDto.class));
    }

    @Test
    @DisplayName("카카오 유저정보를 이용한 로그인 및 회원가 검증")
    public void loginUsingKakaoUserInfoTest() { // 추후 작성
        // Arrange

        // Act

        // Assert
    }

}
