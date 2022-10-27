package com.dailymap.dailymap.domain.jwt;

import com.dailymap.dailymap.DailymapApplicationTests;
import com.dailymap.dailymap.domain.jwt.dto.TokenDto;
import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static com.dailymap.dailymap.domain.jwt.constant.GrantType.BEARER;

@ActiveProfiles("test")
public class TokenManagerTests extends DailymapApplicationTests {

    @Autowired
    private TokenManager sut;

    @Test
    @DisplayName("JWT 정보를 담은 TokenDto 정상 생성 검증")
    public void memberCreateTokenDtoSucceed() {
        // Arrange

        // Act
        TokenDto tokenDto = sut.createTokenDto("test@email.com");

        // Assert
        Assertions.assertEquals(BEARER.getType(),tokenDto.getGrantType());
    }

    @Test
    @DisplayName("TokenManager 클래스를 통해 생성한 JWT 이메일 검증")
    public void tokenManagerCreateJwtEmailValidate() {
        // Arrange
        TokenDto tokenDto = sut.createTokenDto("test@email.com");
        String accessToken = tokenDto.getAccessToken();

        // Act
        String email = sut.getMemberEmail(accessToken);

        // Assert
        Assertions.assertEquals("test@email.com", email);
    }

    @Test
    @DisplayName("TokenManager 클래스를 통해 생성한 JWT 유효성 검증")
    public void tokenManagerCreateJwtValidate() {
        // Arrange
        TokenDto tokenDto = sut.createTokenDto("test@email.com");
        String accessToken = tokenDto.getAccessToken();

        // Act
        boolean validateToken = sut.validateToken(accessToken);

        // Assert
        Assertions.assertTrue(validateToken);
    }

    @Test
    @DisplayName("TokenManager 클래스를 통해 생성한 JWT 유효기간 검증")
    public void tokenManagerCreateJwtExpiredFalse() {
        // Arrange
        TokenDto tokenDto = sut.createTokenDto("test@email.com");
        Date expireTime = tokenDto.getAccessTokenExpireTime();

        // Act
        boolean isTokenExpired = sut.isTokenExpired(expireTime);

        // Assert
        Assertions.assertFalse(isTokenExpired);
    }

}
