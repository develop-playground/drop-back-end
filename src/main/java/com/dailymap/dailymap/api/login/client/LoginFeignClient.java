package com.dailymap.dailymap.api.login.client;

import com.dailymap.dailymap.api.login.dto.KakaoUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoLoginFeignClient",url = "https://kapi.kakao.com")
public interface LoginFeignClient {

    @PostMapping(value = "/v2/user/me")
    KakaoUserInfo getKakaoUserInfo(@RequestHeader("Authorization") String authorization);

}
