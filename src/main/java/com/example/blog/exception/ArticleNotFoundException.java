package com.example.blog.exception;

public class ArticleNotFoundException extends RuntimeException{
    private static final String MESSAGE = "게시글을 찾을 수 없습니다.";
    public ArticleNotFoundException() {
        super(MESSAGE);
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ArticleNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
