package com.dailymap.dailymap.global.error.exception;

public class BusinessException extends RuntimeException {

    private int status;

    public BusinessException(int status, String message) {
        super(message);
        this.status = status;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }

}
