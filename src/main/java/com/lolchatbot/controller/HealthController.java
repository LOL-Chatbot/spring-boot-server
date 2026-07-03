package com.lolchatbot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lolchatbot.common.ApiResponse;
import com.lolchatbot.fastapi.FastApiClient;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 앱 서버와 FastAPI 상태 확인 API이다.
 *
 * @author 김진우
 */
@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    private final FastApiClient fastApiClient;

    public HealthController(FastApiClient fastApiClient) {
        this.fastApiClient = fastApiClient;
    }

    @GetMapping
    public ApiResponse<Map<String, Object>> health() {
        JsonNode fastApiHealth = fastApiClient.get("/health", Map.of());

        return ApiResponse.success(
                Map.of(
                        "status", "ok",
                        "service", "lol-chatbot-back",
                        "fastapi", fastApiHealth
                ),
                "서버 상태를 조회했습니다."
        );
    }
}
