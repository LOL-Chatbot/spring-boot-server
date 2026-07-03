package com.lolchatbot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lolchatbot.common.ApiResponse;
import com.lolchatbot.fastapi.FastApiClient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Android 앱용 챗봇 API이다.
 *
 * @author 김진우
 */
@RestController
@RequestMapping("/api/v1/chat/messages")
public class ChatController {

    private final FastApiClient fastApiClient;

    public ChatController(FastApiClient fastApiClient) {
        this.fastApiClient = fastApiClient;
    }

    @PostMapping
    public ApiResponse<JsonNode> chat(@Valid @RequestBody ChatRequest request) {
        JsonNode response = fastApiClient.post("/chat", request);
        return ApiResponse.success(response.get("data"), "챗봇 답변을 생성했습니다.");
    }

    @PostMapping(value = "/stream", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chatStream(@Valid @RequestBody ChatRequest request) {
        return fastApiClient.postStream("/chat/stream", request);
    }

    public record ChatRequest(
            @NotBlank String message,
            String champion_id,
            String position
    ) {
    }
}
