package com.lolchatbot.config;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * FastAPI 호출용 WebClient 설정이다.
 *
 * @author 김진우
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient fastApiWebClient(FastApiProperties properties) {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(properties.timeoutMillis()));

        return WebClient.builder()
                .baseUrl(properties.baseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
