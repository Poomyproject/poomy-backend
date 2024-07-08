package com.poomy.mainserver.user.controller;

import com.poomy.mainserver.user.api.UserApi;
import com.poomy.mainserver.user.dto.RegisterNickNameReqDto;
import com.poomy.mainserver.user.dto.LoginGoogleReqDto;
import com.poomy.mainserver.user.dto.LoginPoomyReqDto;
import com.poomy.mainserver.user.dto.UserResDto;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.mapper.UserMapper;
import com.poomy.mainserver.user.service.GoogleService;
import com.poomy.mainserver.user.service.JWTService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
        Integer userId = userService.getUserId();
        User user = userService.registerNickName(userId, registerNickNameReqDto.getNickName());
        return ResponseEntity.ok(new ApiResult<>(userMapper.toUserResDto(user)));
    }

}
