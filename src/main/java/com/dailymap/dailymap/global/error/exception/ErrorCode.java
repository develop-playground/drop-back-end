package com.dailymap.dailymap.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 인증
    NOT_VALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    INVALID_MEMBER_TYPE(401, "잘못된 회원 타입입니다. (memberType: KAKAO"),
    REFRESH_TOKEN_EXPIRED(401, "해당 리프레시 토큰은 만료됐습니다."),
    ACCESS_TOKEN_EXPIRED(401, "해당 액세스 토큰은 만료됐습니다."),
    NOT_EXISTS_AUTHORIZATION(401, "Authorization Header가 빈 값 입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(401, "인증 타입이 Bearer 타입이 아닙니다."),
    NOT_ACCESS_TOKEN_TYPE(401, "토큰 타입이 액세스 토큰이 아닙니다."),
    NOT_REFRESH_TOKEN_TYPE(401, "토큰 타입이 리프레시 토큰이 아닙니다."),

    // 회원
    MEMBER_NOT_EXISTS(400, "해당 회원은 존재하지 않습니다."),
    MEMBER_NOT_EXISTS_BY_REFRESH_TOKEN(400, "해당 리프레시 토큰을 가진 회원은 존재하지 않습니다."),
    MEMBER_NOY_EXISTS_BY_ACCESS_TOKEN(400, "해당 액세스 토큰을 가진 회원은 존재하지 않습니다."),

    // 추억
    MEMORY_NOT_EXISTS_BY_ID(401, "해당 ID에 해당하는 추억이 존재하지 않습니다."),
    ;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;
}
