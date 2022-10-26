package com.dailymap.dailymap.api.login;

import com.dailymap.dailymap.api.login.client.KakaoTokenFeignClient;
import com.dailymap.dailymap.api.login.dto.KakaoTokenRequestDto;
import com.dailymap.dailymap.api.login.service.KakaoLoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class KakaoLoginServiceTests {

    @Mock
    KakaoTokenFeignClient feignClient;

    @Test
    @DisplayName("FeignClient를 이용한 카카오 API 호출 검증")
    public void kakaoAPICallVerificationTest() {
        // Arrange
        KakaoLoginService sut = new KakaoLoginService(feignClient);

        // Act
        sut.getKakaoTokenDto(
            "code",
            "clientId",
            "clientSecret",
            "redirectUri"
        );

        // Assert
        verify(feignClient, times(1)).getKakaoToken(any(KakaoTokenRequestDto.class));
    }

}
