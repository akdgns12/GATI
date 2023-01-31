package com.family.gati.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("토큰 생성 실패");
    }

    private InvalidTokenException(String message) {
        super(message);
    }
}
