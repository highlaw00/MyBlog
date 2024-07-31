package com.example.blog.exception;

public class MemberDuplicatedException extends RuntimeException {
    private static final String message = "사용자 이름이 중복 되었습니다.";

    public MemberDuplicatedException() {
        super(message);
    }

    public MemberDuplicatedException(String message) {
        super(message);
    }

    public MemberDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected MemberDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
