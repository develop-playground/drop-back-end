package com.dailymap.dailymap.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 인증
    NOT_VALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    ;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;
}
