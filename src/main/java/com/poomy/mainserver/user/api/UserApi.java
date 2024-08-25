package com.poomy.mainserver.user.api;

import com.poomy.mainserver.user.dto.req.*;
import com.poomy.mainserver.user.dto.res.UserInfoResDto;
import com.poomy.mainserver.user.dto.res.UserMoodResDto;
import com.poomy.mainserver.user.dto.res.UserResDto;
import com.poomy.mainserver.user.dto.res.UserSpotResDto;
import com.poomy.mainserver.user.dto.swagger.UserApiResult;
import com.poomy.mainserver.user.dto.swagger.UserMoodApiResult;
import com.poomy.mainserver.user.dto.swagger.UserSpotApiResult;
import com.poomy.mainserver.util.api.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User API", description = "User 관련된 API")
@RequestMapping("/api/users")
public interface UserApi {

    @Operation(summary = "구글 로그인", description = "Front-end로부터 id_token을 받아서 Poomy Service 회원가입 및 로그인을 진행한다.")
    @ApiResponse(responseCode = "200", description = "OK",
            headers = @Header(name = "accessToken", description = "유저 권한을 위한 jwt 토큰"),
            content = @Content(schema = @Schema(implementation = UserApiResult.class))
    )
    @PostMapping("/login/google")
    ResponseEntity<ApiResult<UserResDto>> loginGoogle(@Valid @RequestBody LoginGoogleReqDto loginGoogleReqDto);

    @Operation(summary = "Poomy 로그인", description = "개발할 때, jwt 토큰 발급용으로 만들어졌다. 이미 구글로 로그인한 경우 한정")
    @ApiResponse(responseCode = "200", description = "OK",
            headers = @Header(name = "accessToken", description = "유저 권한을 위한 jwt 토큰")
//            content = @Content(schema = @Schema(implementation = UserApiResult.class))
    )
    @PostMapping("/login/poomy")
    ResponseEntity<ApiResult<UserResDto>> loginPoomy(@Valid @RequestBody LoginPoomyReqDto loginGoogleReqDto);

    @Operation(summary = "닉네임이 등록 및 수정", description = "닉네임 중복 여부를 검사하여 중복이 되지 않을 경우 등록한다.")
    @ApiResponse(responseCode = "200", description = "Created",
            content = @Content(schema = @Schema(implementation = UserApiResult.class)))
    @PostMapping("/nickname")
    ResponseEntity<ApiResult<UserResDto>> registerNickname(@Valid @RequestBody NicknameReqDto nicknameReqDto);

    @Operation(summary = "사용자 분위기 등록 및 수정", description = "분위기 등록 및 수정할 때 사용한다.")
    @ApiResponse(responseCode = "200", description = "Created",
            content = @Content(schema = @Schema(implementation = UserMoodApiResult.class)))
    @PostMapping("/moods")
    ResponseEntity<ApiResult<List<UserMoodResDto>>> registerUserMoods(@Valid @RequestBody RegisterUserMoodsReqDto registerUserMoodsReqDto);

    @Operation(summary = "사용자 핫플레이스 등록 및 수정", description = "핫플레이스 등록 및 수정할 때 사용한다.")
    @ApiResponse(responseCode = "200", description = "Created",
            content = @Content(schema = @Schema(implementation = UserSpotApiResult.class)))
    @PostMapping("/spots")
    ResponseEntity<ApiResult<List<UserSpotResDto>>> registerUserSpots(@Valid @RequestBody RegisterUserSpotsReqDto registerUserSpotsReqDto);

    @Operation(summary = "닉네임 가능여부 체크", description = "닉네임이 등록 가능한지 여부를 체크한다.")
    @PostMapping("/check/nickname")
    ResponseEntity<ApiResult<String>> checkUserNickname(@Valid @RequestBody NicknameReqDto nicknameReqDto);

    @Operation(summary = "사용자 정보 조회", description = "사용자에 대한 닉네임, 이미지, 취향 등을 조회한다.")
    @GetMapping("")
    ResponseEntity<ApiResult<UserInfoResDto>> getUserInfo();

}
