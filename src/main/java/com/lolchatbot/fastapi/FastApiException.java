package com.lolchatbot.fastapi;

/**
 * FastAPI 연동 실패 예외이다.
 *
 * @author 김진우
 */
public class FastApiException extends RuntimeException {

    public FastApiException(String message) {
        super(message);
    }
}
