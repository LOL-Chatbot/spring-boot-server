package com.lolchatbot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lolchatbot.common.ApiResponse;
import com.lolchatbot.fastapi.FastApiClient;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Android 앱용 챔피언 조회 API이다.
 *
 * @author 김진우
 */
@RestController
@RequestMapping("/api/v1/champions")
public class ChampionController {

    private final FastApiClient fastApiClient;

    public ChampionController(FastApiClient fastApiClient) {
        this.fastApiClient = fastApiClient;
    }

    @GetMapping
    public ApiResponse<JsonNode> champions(@RequestParam(required = false) String position) {
        Map<String, String> params = new HashMap<>();
        params.put("position", position);

        JsonNode response = fastApiClient.get("/champions", params);
        return ApiResponse.success(response.get("data"), "챔피언 목록을 조회했습니다.");
    }

    @GetMapping("/tier-list")
    public ApiResponse<JsonNode> tierList(
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String query
    ) {
        Map<String, String> params = new HashMap<>();
        params.put("position", position);
        params.put("query", query);

        JsonNode response = fastApiClient.get("/champions/tier-list", params);
        return ApiResponse.success(response.get("data"), "챔피언 티어표를 조회했습니다.");
    }

    @GetMapping("/{championId}")
    public ApiResponse<JsonNode> champion(@PathVariable String championId) {
        JsonNode response = fastApiClient.get("/champions/" + championId, new HashMap<>());
        return ApiResponse.success(response.get("data"), "챔피언 정보를 조회했습니다.");
    }
}
