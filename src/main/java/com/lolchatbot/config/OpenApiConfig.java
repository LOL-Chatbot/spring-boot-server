package com.lolchatbot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger UI에 노출할 OpenAPI 문서 기본 설정이다.
 *
 * @author 김진우
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LOL 챗봇 백엔드 API")
                        .description("Spring Boot 백엔드의 FastAPI 중계 및 앱용 API 문서입니다.")
                        .version("v1"))
                .servers(List.of(new Server()
                        .url("http://localhost:8080")
                        .description("로컬 백엔드 서버")));
    }
}
