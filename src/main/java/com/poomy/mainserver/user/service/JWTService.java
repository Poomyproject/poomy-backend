package com.poomy.mainserver.user.service;

import com.poomy.mainserver.user.entity.UserEntity;
import com.poomy.mainserver.util.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JWTService {

    private final JWTUtil jwtUtil;

    public String createJwt(UserEntity userEntity){
        String googleEmail = userEntity.getGoogleEmail();
        String role = userEntity.getRole().name();
        String token = jwtUtil.createJwt(googleEmail, role, 1000 * 60 * 60L);
        return "Bearer "+token;
    }

}
