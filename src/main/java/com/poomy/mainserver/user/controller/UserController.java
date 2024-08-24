package com.poomy.mainserver.user.controller;

import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.service.MoodService;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.spot.service.SpotService;
import com.poomy.mainserver.user.api.UserApi;
import com.poomy.mainserver.user.dto.*;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserMood;
import com.poomy.mainserver.user.entity.UserSpot;
import com.poomy.mainserver.user.mapper.UserMapper;
import com.poomy.mainserver.user.service.GoogleService;
import com.poomy.mainserver.user.service.JWTService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
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
    private final MoodService moodService;
    private final SpotService spotService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<ApiResult<UserResDto>> loginGoogle(LoginGoogleReqDto loginGoogleReqDto) {

        String googleEmail = googleService.extractGoogleEmail(loginGoogleReqDto.getIdToken());
        User user = userService.loginGoogle(googleEmail);
        String jwtToken = jwtService.createJwt(user);
        return ResponseEntity.ok()
                .header("accessToken", jwtToken)
                .body(ApiUtils.success(userMapper.toUserResDto(user)));
    }

    @Override
    public ResponseEntity<ApiResult<UserResDto>> loginPoomy(LoginPoomyReqDto loginPoomyReqDto) {
        User user = userService.loginPoomy(loginPoomyReqDto.getGoogleEmail());
        String jwtToken = jwtService.createJwt(user);
        return ResponseEntity.ok()
                .header("accessToken", jwtToken)
                .body(ApiUtils.success(userMapper.toUserResDto(user)));
    }

    @Override
    public ResponseEntity<ApiResult<UserResDto>> registerNickname(RegisterNicknameReqDto registerNicknameReqDto) {
        log.info("register nickname : {}", registerNicknameReqDto.getNickname());
        User user = userService.getUser();
        user = userService.registerNickname(user, registerNicknameReqDto.getNickname());
        return ResponseEntity.created(null).body(ApiUtils.success(userMapper.toUserResDto(user)));
    }

    @Override
    public ResponseEntity<ApiResult<List<UserMoodResDto>>> registerUserMoods(RegisterUserMoodsReqDto registerUserMoodsReqDto) {
        List<Integer> moodIds = registerUserMoodsReqDto.getMoodIds();
        List<Mood> moods = moodService.getMoods(moodIds);
        List<UserMood> userMoods = userService.registerUserMood(moods);
        List<UserMoodResDto> userMoodResDtos = userMoods.stream()
                .map(userMapper::toUserMoodResDto)
                .toList();
        log.info("userAtmosphereResDtos : {}", userMoodResDtos);
        return ResponseEntity.created(null).body(ApiUtils.success(userMoodResDtos));
    }

    @Override
    public ResponseEntity<ApiResult<List<UserSpotResDto>>> registerUserSpots(RegisterUserSpotsReqDto registerUserSpotsReqDto) {
        List<Integer> spotIds = registerUserSpotsReqDto.getSpotIds();
        List<Spot> spots = spotService.getSpots(spotIds);
        List<UserSpot> userSpots = userService.registerUserSpot(spots);
        List<UserSpotResDto> userSpotResDtos = userSpots.stream()
                .map(userMapper::toUserSpotResDto)
                .toList();
        return ResponseEntity.created(null).body(ApiUtils.success(userSpotResDtos));
    }

}
