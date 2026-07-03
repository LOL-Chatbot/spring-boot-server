package com.lolchatbot.fastapi;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

/**
 * FastAPI AI 서버 호출 클라이언트이다.
 *
 * @author 김진우
 */
@Component
public class FastApiClient {

    private final WebClient fastApiWebClient;

    public FastApiClient(WebClient fastApiWebClient) {
        this.fastApiWebClient = fastApiWebClient;
    }

    public JsonNode get(String path, Map<String, String> queryParams) {
        try {
            return fastApiWebClient.get()
                    .uri(uriBuilder -> {
                        var builder = uriBuilder.path(path);
                        queryParams.forEach((key, value) -> {
                            if (value != null && !value.isBlank()) {
                                builder.queryParam(key, value);
                            }
                        });
                        return builder.build();
                    })
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();
        } catch (WebClientResponseException exception) {
            throw new FastApiException(extractMessage(exception));
        } catch (RuntimeException exception) {
            throw new FastApiException("FastAPI 서버 요청에 실패했습니다.");
        }
    }

    public JsonNode post(String path, Object requestBody) {
        try {
            return fastApiWebClient.post()
                    .uri(path)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .onErrorResume(WebClientResponseException.class, exception -> Mono.error(new FastApiException(extractMessage(exception))))
                    .block();
        } catch (FastApiException exception) {
            throw exception;
        } catch (RuntimeException exception) {
            throw new FastApiException("FastAPI 서버 요청에 실패했습니다.");
        }
    }

    public String postStream(String path, Object requestBody) {
        try {
            return fastApiWebClient.post()
                    .uri(path)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException exception) {
            throw new FastApiException(extractMessage(exception));
        } catch (RuntimeException exception) {
            throw new FastApiException("FastAPI 스트리밍 요청에 실패했습니다.");
        }
    }

    private String extractMessage(WebClientResponseException exception) {
        String body = exception.getResponseBodyAsString();
        if (body == null || body.isBlank()) {
            return "FastAPI 서버 요청에 실패했습니다.";
        }
        return body;
    }
}
