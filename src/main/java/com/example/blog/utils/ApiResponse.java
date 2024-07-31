package com.example.blog.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
    응답 형식 (JSON)
    {
      "code": "XXX",
      "status": "success",
      "data": {
        "username": "anonymous",
        "intro": "some intros"
      }
 */
@Getter
public class ApiResponse<T> {
    private int code;
    private String status;
    private String message;
    private T data;

    private final static String SUCCESS_STATUS = "success";
    private final static String FAILURE_STATUS = "failure";

    private ApiResponse(T data, HttpStatus code, String status, String message) {
        this.code = code.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private ApiResponse(T data, HttpStatus code, String status) {
        this(data, code, status, null);
    }


    public static <T> ApiResponse<T> createSuccessResponse(T data) {
        return new ApiResponse<>(data, HttpStatus.OK, SUCCESS_STATUS);
    }

    public static <T> ApiResponse<T> createSuccessResponse(T data, HttpStatus statusCode) {
        return new ApiResponse<>(data, statusCode, SUCCESS_STATUS);
    }

    public static <T> ApiResponse<T> createFailureResponse(T data, HttpStatus statusCode) {
        return new ApiResponse<>(data, statusCode, FAILURE_STATUS);
    }

    public static <T> ApiResponse<T> createFailureResponse(T data, HttpStatus statusCode, String additionalMessage) {
        return new ApiResponse<>(data, statusCode, FAILURE_STATUS, additionalMessage);
    }
}
