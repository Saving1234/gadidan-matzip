package com.gadidan.app.common;

/// 데이터베이스 연결을 생성하지 못했을 때 발생하는 예외입니다.
public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
