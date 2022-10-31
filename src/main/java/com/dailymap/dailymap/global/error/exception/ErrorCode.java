package com.dailymap.dailymap.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 인증
    NOT_VALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    INVALID_MEMBER_TYPE(401, "잘못된 회원 타입입니다. (memberType: KAKAO"),

    // 회원
    MEMBER_NOT_EXISTS(400, "해당 회원은 존재하지 않습니다."),
    ;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;
}
