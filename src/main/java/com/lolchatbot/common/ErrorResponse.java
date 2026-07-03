package com.lolchatbot.common;

/**
 * 앱 공통 오류 응답 형식이다.
 *
 * @author 김진우
 */
public record ErrorResponse(
        String code,
        String message
) {
}
