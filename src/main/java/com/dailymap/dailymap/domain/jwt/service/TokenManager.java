package com.dailymap.dailymap.domain.jwt.service;

import com.dailymap.dailymap.domain.jwt.constant.GrantType;
import com.dailymap.dailymap.domain.jwt.constant.TokenType;
import com.dailymap.dailymap.domain.jwt.dto.TokenDto;
import com.dailymap.dailymap.global.error.exception.ErrorCode;
import com.dailymap.dailymap.global.error.exception.NotValidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.NOT_VALID_TOKEN;

@Slf4j
@Component
public class TokenManager {

	@Value("${token.access-token-expired-time}")
	private String accessTokenExpiredTime;

	@Value("${token.refresh-token-expired-time}")
	private String refreshTokenExpiredTime;

	@Value("${token.secret}")
	private String tokenSecret;

	public TokenDto createTokenDto(String email) {
		Date accessTokenExpireTime = createTokenExpireTime(accessTokenExpiredTime);
		Date refreshTokenExpireTime = createTokenExpireTime(refreshTokenExpiredTime);

		String accessToken = createToken(email, TokenType.ACCESS, accessTokenExpireTime);
		String refreshToken = createToken(email, TokenType.REFRESH, refreshTokenExpireTime);

		GrantType grantType = GrantType.BEARER;

		return TokenDto.builder()
			.grantType(grantType.getType())
			.accessToken(accessToken)
			.accessTokenExpireTime(accessTokenExpireTime)
			.refreshToken(refreshToken)
			.refreshTokenExpireTime(refreshTokenExpireTime)
			.build();
	}

	private Date createTokenExpireTime(String tokenExpireTime) {
		return new Date(System.currentTimeMillis() + Long.parseLong(tokenExpireTime));
	}

	private String createToken(String email, TokenType tokenType, Date expireTime) {
		return Jwts.builder()
			.setSubject(tokenType.name())
			.setAudience(email)
			.setIssuedAt(new Date())
			.setExpiration(expireTime)
			.signWith(SignatureAlgorithm.HS512, tokenSecret)
			.setHeaderParam("typ", "JWT")
			.compact();
	}

	public String getMemberEmail(String accessToken) {
		String email;

		try {
			Claims claims = getTokenClaims(accessToken);
			email = claims.getAudience();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotValidTokenException(NOT_VALID_TOKEN);
		}
		return email;
	}

	public boolean validateToken(String token) {
		try {
			getTokenClaims(token);
			return true;
		} catch (JwtException e) {
			log.info("잘못된 JWT");
		} catch (Exception e) {
			log.info("JWT 검증 중 에러 발생");
		}

		return false;
	}

	public Claims getTokenClaims(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(tokenSecret)
				.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotValidTokenException(NOT_VALID_TOKEN);
		}
		return claims;
	}

	public boolean isTokenExpired(Date tokenExpireTime) {
		Date now = new Date();
		if (now.after(tokenExpireTime)) {
			return true;
		}
		return false;
	}

}
