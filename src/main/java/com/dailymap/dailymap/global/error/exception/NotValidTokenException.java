package com.dailymap.dailymap.global.error.exception;

public class NotValidTokenException extends BusinessException{
    public NotValidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
