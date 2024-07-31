package com.example.blog.exception;

public class MemberNotFoundException extends RuntimeException {
    private static final String message = "존재하지 않는 사용자입니다.";

    public MemberNotFoundException() {
        super(message);
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MemberNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
