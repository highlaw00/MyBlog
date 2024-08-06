package com.example.blog.exception;

public class CommentNotFoundException extends RuntimeException {
    private final static String MESSAGE = "댓글을 찾을 수 없습니다.";
    public CommentNotFoundException() {
        super(MESSAGE);
    }
}
