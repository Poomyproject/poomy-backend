package com.poomy.mainserver.user.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;

import java.io.StringReader;
import java.security.PrivateKey;
import java.security.Security;
import java.util.Date;

public class AppleClientSecretGenerator {

    public static String generateClientSecret(String teamId, String clientId, String keyId, String privateKeyContent) {
        try {
            // PrivateKey 로드
            PrivateKey privateKey = loadPrivateKey(privateKeyContent);

            // 현재 시간과 만료 시간 설정
            long now = System.currentTimeMillis();
            Date issueAt = new Date(now);
            Date expireAt = new Date(now + (1000 * 60 * 5)); // 5분 후 만료

            // JWT 생성
            return Jwts.builder()
                    .setHeaderParam("kid", keyId)
                    .setHeaderParam("alg", "ES256")
                    .setIssuer(teamId)
                    .setAudience("https://appleid.apple.com")
                    .setSubject(clientId)
                    .setExpiration(expireAt)
                    .setIssuedAt(issueAt)
                    .signWith(privateKey)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate client secret. Details: " + e.getMessage(), e);
        }
    }

    private static PrivateKey loadPrivateKey(String privateKeyContent) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        if (privateKeyContent == null || privateKeyContent.isEmpty()) {
            throw new RuntimeException("Private key content is null or empty");
        }

        // 디버깅: 원본 privateKeyContent 출력
        System.out.println("Original Private Key Content:\n" + privateKeyContent);

        // PEM 형식 정리
        String cleanPrivateKey = privateKeyContent
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .trim(); // 앞뒤 공백만 제거

        // 디버깅: 정리된 cleanPrivateKey 출력
//        System.out.println("Clean Private Key:\n" + cleanPrivateKey);

        if (cleanPrivateKey.isEmpty()) {
            throw new RuntimeException("Cleaned private key content is empty after processing");
        }

        // Base64 디코딩
        byte[] keyBytes;
        try {
            keyBytes = java.util.Base64.getDecoder().decode(cleanPrivateKey);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to decode Base64 private key. Check key format.", e);
        }

        // 디버깅: Base64 디코딩된 키 길이 출력
//        System.out.println("Key Bytes Length: " + keyBytes.length);

        if (keyBytes == null || keyBytes.length == 0) {
            throw new RuntimeException("Decoded key bytes are null or empty");
        }

        // PrivateKey 생성
        return new JcaPEMKeyConverter()
                .setProvider("BC")
                .getPrivateKey(PrivateKeyInfo.getInstance(keyBytes));
    }

}
