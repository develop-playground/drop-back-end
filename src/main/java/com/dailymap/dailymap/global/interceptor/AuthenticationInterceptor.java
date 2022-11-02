package com.dailymap.dailymap.global.interceptor;

import com.dailymap.dailymap.domain.jwt.service.TokenManager;
import com.dailymap.dailymap.global.error.exception.AuthorizationException;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.dailymap.dailymap.global.error.exception.ErrorCode.*;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws BusinessException {
        String authorization = request.getHeader("Authorization");

        if (!StringUtils.hasText(authorization)) {
            throw new AuthorizationException(NOT_EXISTS_AUTHORIZATION);
        }

        String bearer = authorization.split(" ")[0];
        if (!"Bearer".equals(bearer)) {
            throw new AuthorizationException(NOT_VALID_BEARER_GRANT_TYPE);
        }

        String token = authorization.split(" ")[1];
        if (!tokenManager.validateToken(token)) {
            throw new AuthorizationException(NOT_VALID_TOKEN);
        }

        return true;
    }

}
