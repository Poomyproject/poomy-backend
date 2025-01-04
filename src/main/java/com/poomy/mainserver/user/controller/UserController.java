package com.poomy.mainserver.user.controller;

import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.repository.MoodRepository;
import com.poomy.mainserver.mood.service.MoodService;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.spot.repository.SpotRepository;
import com.poomy.mainserver.spot.service.SpotService;
import com.poomy.mainserver.user.api.UserApi;
import com.poomy.mainserver.user.dto.req.*;
import com.poomy.mainserver.user.dto.res.*;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserMood;
import com.poomy.mainserver.user.entity.UserSpot;
import com.poomy.mainserver.user.mapper.UserMapper;
import com.poomy.mainserver.user.repository.UserMoodRepository;
import com.poomy.mainserver.user.repository.UserSpotRepository;
import com.poomy.mainserver.user.service.AppleService;
import com.poomy.mainserver.user.service.GoogleService;
import com.poomy.mainserver.user.service.JWTService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.UUID;

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
    private final AppleService appleService;
    private final UserMoodRepository userMoodRepository;
    private final UserSpotRepository userSpotRepository;
    private final MoodRepository moodRepository;
    private final SpotRepository spotRepository;


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
    public ResponseEntity<ApiResult<String>> loginGuest() {
        String guestUsername = "guest-" + UUID.randomUUID().toString().substring(0, 8);
        User user = userService.loginGuest(guestUsername);
        String jwtToken = jwtService.createGuestJwt(user);

        Long number1 = 1L;
        Long number2 = 2L;

        Mood mood1 = moodRepository.getMood(number1);
        Mood mood2 = moodRepository.getMood(number2);

        UserMood userMood1 = new UserMood(user, mood1);
        UserMood userMood2 = new UserMood(user, mood2);
        userMoodRepository.save(userMood1);
        userMoodRepository.save(userMood2);

        Spot spot1 = spotRepository.getSpot(number1);
        Spot spot2 = spotRepository.getSpot(number2);

        UserSpot userSpot1 = new UserSpot(user, spot1);
        UserSpot userSpot2 = new UserSpot(user, spot2);
        userSpotRepository.save(userSpot1);
        userSpotRepository.save(userSpot2);

        return ResponseEntity.ok()
                .header("accessToken", jwtToken)
                .body(ApiUtils.success(jwtToken));
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
    public ResponseEntity<ApiResult<UserResDto>> loginApple(LoginAppleReqDto loginAppleReqDto) {
        String authorizationCode = loginAppleReqDto.getAuthorizationCode();

        // Apple 서버에서 토큰 요청 및 응답 처리
        Map<String, Object> tokenResponse = appleService.getTokenFromApple(authorizationCode);
        String appleEmail = (String) tokenResponse.get("email");
        String appleSub = (String) tokenResponse.get("sub");

        // 두 개의 파라미터를 전달
        User user = userService.loginApple(appleEmail, appleSub);

        // JWT 생성
        String jwtToken = jwtService.createJwt(user);

        // 응답 생성
        UserResDto userResDto = userMapper.toUserResDto(user);
        ApiResult<UserResDto> successResponse = new ApiResult<>(true, userResDto);

        return ResponseEntity.ok()
                .header("accessToken", jwtToken)
                .body(successResponse);
    }


    @Override
    public ResponseEntity<ApiResult<UserResDto>> registerNickname(NicknameReqDto nicknameReqDto) {
        log.info("register nickname : {}", nicknameReqDto.getNickname());
        User user = userService.getUser();
        user = userService.registerNickname(user, nicknameReqDto.getNickname());
        return ResponseEntity.ok(ApiUtils.success(userMapper.toUserResDto(user)));
    }

    @Override
    public ResponseEntity<ApiResult<List<UserMoodResDto>>> registerUserMoods(RegisterUserMoodsReqDto registerUserMoodsReqDto) {
        List<Long> moodIds = registerUserMoodsReqDto.getMoodIds();
        List<Mood> moods = moodService.getMoods(moodIds);
        List<UserMood> userMoods = userService.registerUserMood(moods);
        List<UserMoodResDto> userMoodResDtos = userMoods.stream()
                .map(userMapper::toUserMoodResDto)
                .toList();
        log.info("userAtmosphereResDtos : {}", userMoodResDtos);
        return ResponseEntity.ok(ApiUtils.success(userMoodResDtos));
    }

    @Override
    public ResponseEntity<ApiResult<List<UserSpotResDto>>> registerUserSpots(RegisterUserSpotsReqDto registerUserSpotsReqDto) {
        List<Long> spotIds = registerUserSpotsReqDto.getSpotIds();
        List<Spot> spots = spotService.getSpots(spotIds);
        List<UserSpot> userSpots = userService.registerUserSpot(spots);
        List<UserSpotResDto> userSpotResDtos = userSpots.stream()
                .map(userMapper::toUserSpotResDto)
                .toList();
        return ResponseEntity.ok(ApiUtils.success(userSpotResDtos));
    }

    @Override
    public ResponseEntity<ApiResult<String>> checkUserNickname(NicknameReqDto nicknameReqDto) {
        boolean existedNickname = userService.checkUserNickname(nicknameReqDto.getNickname());
        if(existedNickname){
            // 닉네임 존재 -> 등록 불가능
            return ResponseEntity.ok(ApiUtils.error("닉네임이 이미 존재합니다."));
        }
        return ResponseEntity.ok(ApiUtils.success("닉네임 등록이 가능합니다."));
    }

    @Override
    public ResponseEntity<ApiResult<UserInfoResDto>> getUserInfo() {
        UserInfoResDto userInfoResDto = userService.getUserInfo();
        return ResponseEntity.ok(ApiUtils.success(userInfoResDto));
    }

}
