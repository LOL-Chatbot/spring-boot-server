package com.lolchatbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FastAPI AI 서버 연동 설정이다.
 *
 * @author 김진우
 */
@ConfigurationProperties(prefix = "app.fast-api")
public record FastApiProperties(
        String baseUrl,
        int timeoutMillis
) {
}
