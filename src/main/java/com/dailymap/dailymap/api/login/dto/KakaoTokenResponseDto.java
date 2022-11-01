package com.dailymap.dailymap.api.login.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoTokenResponseDto {

	private String token_type;

	private String access_token;

	private Integer expires_in;

	private String refresh_token;

	private Integer refresh_token_expires_in;

	private String scope;

}
