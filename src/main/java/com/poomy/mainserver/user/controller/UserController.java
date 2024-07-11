package com.poomy.mainserver.user.controller;

import com.poomy.mainserver.user.api.UserApi;
import com.poomy.mainserver.user.dto.*;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserAtmosphere;
import com.poomy.mainserver.user.mapper.UserMapper;
import com.poomy.mainserver.user.service.GoogleService;
import com.poomy.mainserver.user.service.JWTService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService userService;
    private final GoogleService googleService;
    private final JWTService jwtService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<ApiResult<?>> loginGoogle(LoginGoogleReqDto loginGoogleReqDto) {

        String googleEmail = googleService.extractGoogleEmail(loginGoogleReqDto.getIdToken());
        User user = userService.loginGoogle(googleEmail);
        String jwtToken = jwtService.createJwt(user);
        return ResponseEntity.ok()
                .header("accessToken", jwtToken)
                .body(new ApiResult<>(userMapper.toUserResDto(user)));
    }

    @Override
    public ResponseEntity<ApiResult<UserResDto>> loginPoomy(LoginPoomyReqDto loginPoomyReqDto) {
        User user = userService.loginPoomy(loginPoomyReqDto.getGoogleEmail());
        String jwtToken = jwtService.createJwt(user);
        return ResponseEntity.ok()
                .header("accessToken", jwtToken)
                .body(new ApiResult<>(userMapper.toUserResDto(user)));
    }

    @Override
    public ResponseEntity<ApiResult<UserResDto>> registerNickName(RegisterNickNameReqDto registerNickNameReqDto) {
        log.info("register nickName : {}", registerNickNameReqDto.getNickName());
        User user = userService.getUser();
        user = userService.registerNickName(user, registerNickNameReqDto.getNickName());
        return ResponseEntity.ok(new ApiResult<>(userMapper.toUserResDto(user)));
    }

    @Override
    public ResponseEntity<ApiResult<List<UserAtmosphereResDto>>> registerUserAtmospheres(RegisterUserAtmospheresReqDto registerUserAtmospheresReqDto) {
        List<UserAtmosphere> userAtmospheres = userService.registerUserAtmosphere(registerUserAtmospheresReqDto);
        List<UserAtmosphereResDto> userAtmosphereResDtos = userAtmospheres.stream()
                .map(userMapper::toUserAtmosphereResDto)
                .toList();
        log.info("userAtmosphereResDtos : {}", userAtmosphereResDtos);
        return ResponseEntity.ok(new ApiResult<>(userAtmosphereResDtos));
    }

}
