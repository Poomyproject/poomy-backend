package com.poomy.mainserver.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.poomy.mainserver.config.AppleConfig;
import com.poomy.mainserver.user.dto.req.AppleTokenRequest;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.util.exception.InvalidTokenException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Component
@Service
public class AppleService {

    private final AppleConfig appleConfig;
    private final AppleTokenRequest appleTokenRequest;

    public AppleService(AppleConfig appleConfig, AppleTokenRequest appleTokenRequest) {
        this.appleConfig = appleConfig;
        this.appleTokenRequest = appleTokenRequest;
    }

    public Map<String, Object> getTokenFromApple(String authorizationCode) {
        // 클라이언트 시크릿 생성
        String clientSecret = AppleClientSecretGenerator.generateClientSecret(
                appleConfig.getTeamId(),
                appleConfig.getClientId(),
                appleConfig.getKeyId(),
                appleConfig.getPrivateKey()
        );

        // AppleTokenRequest 인스턴스를 사용하여 토큰 요청
        return appleTokenRequest.requestToken(clientSecret, authorizationCode);
    }

    public boolean checkUserInfoStatus(User user) {
        // Apple 사용자 정보의 유효성을 검증
        return user != null && user.getGoogleEmail() != null;
    }
}
