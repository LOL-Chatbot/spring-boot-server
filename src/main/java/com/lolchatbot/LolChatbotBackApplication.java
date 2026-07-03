package com.lolchatbot;

import com.lolchatbot.config.FastApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * LOL 챗봇 Android 앱 전용 Spring Boot BFF 애플리케이션이다.
 *
 * @author 김진우
 */
@SpringBootApplication
@EnableConfigurationProperties(FastApiProperties.class)
public class LolChatbotBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LolChatbotBackApplication.class, args);
    }
}
