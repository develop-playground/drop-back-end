package com.dailymap.dailymap.global.interceptor;

import com.dailymap.dailymap.domain.jwt.constant.TokenType;
import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import com.dailymap.dailymap.global.error.exception.AuthorizationException;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.ACCESS_TOKEN_EXPIRED;
import static com.dailymap.dailymap.global.error.exception.ErrorCode.NOT_ACCESS_TOKEN_TYPE;

@Component
@RequiredArgsConstructor
public class AccessTokenUseInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws BusinessException {
        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        Claims claims = tokenManager.getTokenClaims(accessToken);

        String tokenType = claims.getSubject();
        if (!TokenType.isAccessToken(tokenType)) {
            throw new AuthorizationException(NOT_ACCESS_TOKEN_TYPE);
        }

        Date expireTime = claims.getExpiration();
        if (tokenManager.isTokenExpired(expireTime)) {
            throw new AuthorizationException(ACCESS_TOKEN_EXPIRED);
        }

        return true;
    }

}
