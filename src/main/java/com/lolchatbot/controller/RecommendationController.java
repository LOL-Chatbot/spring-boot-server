package com.lolchatbot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lolchatbot.common.ApiResponse;
import com.lolchatbot.fastapi.FastApiClient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Android 앱용 추천 중계 API이다.
 *
 * @author 김진우
 */
@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    private final FastApiClient fastApiClient;

    public RecommendationController(FastApiClient fastApiClient) {
        this.fastApiClient = fastApiClient;
    }

    @PostMapping("/champion-build")
    public ApiResponse<JsonNode> championBuild(@Valid @RequestBody RecommendationRequest request) {
        return proxy("/recommendations/champion-build", request, "챔피언 종합 빌드를 조회했습니다.");
    }

    @PostMapping("/runes")
    public ApiResponse<JsonNode> runes(@Valid @RequestBody RecommendationRequest request) {
        return proxy("/recommendations/runes", request, "룬 추천을 조회했습니다.");
    }

    @PostMapping("/spells")
    public ApiResponse<JsonNode> spells(@Valid @RequestBody RecommendationRequest request) {
        return proxy("/recommendations/spells", request, "스펠 추천을 조회했습니다.");
    }

    @PostMapping("/items")
    public ApiResponse<JsonNode> items(@Valid @RequestBody RecommendationRequest request) {
        return proxy("/recommendations/items", request, "아이템 추천을 조회했습니다.");
    }

    @PostMapping("/skills")
    public ApiResponse<JsonNode> skills(@Valid @RequestBody RecommendationRequest request) {
        return proxy("/recommendations/skills", request, "스킬 추천을 조회했습니다.");
    }

    @PostMapping("/counters")
    public ApiResponse<JsonNode> counters(@Valid @RequestBody RecommendationRequest request) {
        return proxy("/recommendations/counters", request, "카운터 챔피언을 조회했습니다.");
    }

    private ApiResponse<JsonNode> proxy(String path, RecommendationRequest request, String message) {
        JsonNode response = fastApiClient.post(path, request);
        return ApiResponse.success(response.get("data"), message);
    }

    public record RecommendationRequest(
            @NotBlank String champion_id,
            @NotBlank String position,
            String enemy_champion_id,
            String play_style
    ) {
    }
}
