package com.poomy.mainserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppleConfig {
    @Value("${spring.apple.team-id}")
    private String teamId;

    @Value("${spring.apple.client-id}")
    private String clientId;

    @Value("${spring.apple.key-id}")
    private String keyId;

    @Value("${spring.apple.private-key}")
    private String privateKey;

    public String getTeamId() {
        return teamId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}

