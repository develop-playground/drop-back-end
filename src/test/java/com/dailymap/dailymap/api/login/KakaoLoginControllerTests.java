package com.dailymap.dailymap.api.login;

import com.dailymap.dailymap.DailymapApplicationTests;
import com.dailymap.dailymap.api.login.controller.KakaoLoginController;
import com.dailymap.dailymap.api.login.dto.KakaoTokenResponseDto;
import com.dailymap.dailymap.api.login.service.KakaoLoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KakaoLoginControllerTests extends DailymapApplicationTests {

    @Mock
    KakaoLoginService kakaoLoginService;

    @Test
    @DisplayName("카카오 토큰 생성 성공 검증")
    public void getKakaoTokenDtoTest() {
        // Arrange
        KakaoLoginController sut = new KakaoLoginController(kakaoLoginService);
        when(kakaoLoginService.getKakaoTokenDto(
            "code",
            null,
            null,
            null
            )
        ).thenReturn(
            KakaoTokenResponseDto.builder()
                .access_token("test.access.token")
                .build()
        );

        // Act
        ResponseEntity<KakaoTokenResponseDto> responseBody = sut.loginCallback("code");

        // Assert
        Assertions.assertEquals(200, responseBody.getStatusCodeValue());
        Assertions.assertEquals("test.access.token", responseBody.getBody().getAccess_token());
    }

}
