package com.dailymap.dailymap.domain.jwt.constant;

import lombok.Getter;

@Getter
public enum TokenType {

	ACCESS,
	REFRESH,
	;

	public static boolean isAccessToken(String tokenType) {
		String accessTokenName = ACCESS.name();
		return accessTokenName.equals(tokenType);
	}

}
