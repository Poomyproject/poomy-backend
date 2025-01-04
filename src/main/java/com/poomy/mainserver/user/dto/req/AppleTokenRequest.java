package com.poomy.mainserver.user.dto.req;

import com.poomy.mainserver.config.AppleConfig;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AppleTokenRequest {

    private static final String TOKEN_URL = "https://appleid.apple.com/auth/token";

    private final AppleConfig appleConfig;

    public AppleTokenRequest(AppleConfig appleConfig) {
        this.appleConfig = appleConfig;
    }

    public Map<String, Object> requestToken(String clientSecret, String authorizationCode) {
        RestTemplate restTemplate = new RestTemplate();

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 바디 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", appleConfig.getClientId());
        body.add("client_secret", clientSecret);
        body.add("grant_type", "authorization_code");
        body.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            // Apple 서버로 요청
            ResponseEntity<Map> response = restTemplate.exchange(
                    TOKEN_URL,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw new RuntimeException("Failed to get token from Apple: " + response.getStatusCode());
            }

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while requesting token from Apple", e);
        }
    }
}
