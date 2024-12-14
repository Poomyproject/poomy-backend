package com.poomy.mainserver.user.service;

import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.type.UserRoleType;
import com.poomy.mainserver.util.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JWTService {

    private final JWTUtil jwtUtil;

    public String createJwt(User user){
        String googleEmail = user.getGoogleEmail();
        String role = user.getRole().name();
        String token = jwtUtil.createJwt(googleEmail, role, 1000 * 60 * 60L);
        return "Bearer "+token;
    }

    public String createGuestJwt(User user){
        String role = user.getRole().name();
        String guestUserName = user.getGoogleEmail();
        String token = jwtUtil.createGuestJwt(guestUserName, role, 1000 * 60 * 60L);
        return "Bearer "+token;
    }

}
