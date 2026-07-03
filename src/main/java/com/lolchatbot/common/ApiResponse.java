package com.lolchatbot.common;

/**
 * 앱 공통 API 응답 형식이다.
 *
 * @author 김진우
 */
public record ApiResponse<T>(
        boolean success,
        T data,
        String message,
        ErrorResponse error
) {

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message, null);
    }

    public static <T> ApiResponse<T> failure(String code, String message) {
        return new ApiResponse<>(false, null, null, new ErrorResponse(code, message));
    }
}
