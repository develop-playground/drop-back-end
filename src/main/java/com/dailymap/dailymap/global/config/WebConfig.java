package com.dailymap.dailymap.global.config;

import com.dailymap.dailymap.global.interceptor.AccessTokenUseInterceptor;
import com.dailymap.dailymap.global.interceptor.AuthenticationInterceptor;
import com.dailymap.dailymap.global.interceptor.RefreshTokenUseInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor requestHeaderInterceptor;

    private final AccessTokenUseInterceptor accessTokenUseInterceptor;

    private final RefreshTokenUseInterceptor refreshTokenUseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestHeaderInterceptor)
            .order(1)
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/health")
            .excludePathPatterns("/api/auth/kakao/callback")
            .excludePathPatterns("/api/auth/login")
        ;
        registry.addInterceptor(refreshTokenUseInterceptor)
            .order(2)
            .addPathPatterns("/api/auth/**")
            .excludePathPatterns("/api/auth/kakao/callback")
            .excludePathPatterns("/api/auth/login")
            .excludePathPatterns("api/auth/refreshtoken")
        ;
        registry.addInterceptor(accessTokenUseInterceptor)
            .order(3)
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/health")
            .excludePathPatterns("/api/auth/**")
        ;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedHeaders("*")
            .allowedOrigins("*")
//            .allowedMethods(
//                HttpMethod.GET.name(),
//                HttpMethod.HEAD.name(),
//                HttpMethod.POST.name(),
//                HttpMethod.OPTIONS.name(),
//                HttpMethod.PATCH.name(),
//                HttpMethod.DELETE.name()
//            )

//            .allowedMethods("GET", "HEAD", "POST", "OPTIONS", "PATCH", "DELETE")
            .allowedMethods("*");
    }

}
