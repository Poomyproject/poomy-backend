package com.poomy.mainserver.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret){
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getGoogleEmail(String token){
        return getPayload(token).get("googleEmail", String.class);
    }

    public String getRole(String token){
        return getPayload(token).get("role", String.class);
    }

    public Boolean isExpired(String token){
        return getPayload(token).getExpiration().before(new Date());
    }

    private Claims getPayload(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public String createJwt(String googleEmail, String role, Long expiredMs){
        return Jwts.builder()
                .claim("googleEmail", googleEmail)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }


}
