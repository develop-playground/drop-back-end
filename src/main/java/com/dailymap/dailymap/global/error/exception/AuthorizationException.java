package com.dailymap.dailymap.global.error.exception;

public class AuthorizationException extends BusinessException{
    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
