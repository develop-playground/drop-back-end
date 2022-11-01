package com.dailymap.dailymap.api.login.client;


import com.dailymap.dailymap.api.login.dto.KakaoTokenRequestDto;
import com.dailymap.dailymap.api.login.dto.KakaoTokenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "kakaoTokenFeignClient", url = "https://kauth.kakao.com")
public interface KakaoTokenFeignClient {

	@PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded;charset=utf-8")
	KakaoTokenResponseDto getKakaoToken(@SpringQueryMap KakaoTokenRequestDto request);

}
