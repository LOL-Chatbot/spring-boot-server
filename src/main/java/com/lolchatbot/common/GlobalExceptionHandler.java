package com.lolchatbot.common;

import com.lolchatbot.fastapi.FastApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 앱 공통 예외 응답 변환기이다.
 *
 * @author 김진우
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FastApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleFastApiException(FastApiException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(ApiResponse.failure("FASTAPI_REQUEST_FAILED", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException() {
        return ResponseEntity.unprocessableEntity()
                .body(ApiResponse.failure("VALIDATION_ERROR", "요청 데이터 검증에 실패했습니다."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception exception) {
        return ResponseEntity.internalServerError()
                .body(ApiResponse.failure("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다."));
    }
}
