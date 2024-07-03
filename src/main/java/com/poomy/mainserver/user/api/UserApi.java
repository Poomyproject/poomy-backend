package com.poomy.mainserver.user.api;

import com.poomy.mainserver.user.dto.RegisterNickNameReqDto;
import com.poomy.mainserver.user.dto.LoginGoogleReqDto;
import com.poomy.mainserver.user.dto.LoginPoomyReqDto;
import com.poomy.mainserver.user.dto.UserResDto;
import com.poomy.mainserver.util.api.ApiErrorResult;
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

@Tag(name = "User API", description = "User 관련된 API")
@RequestMapping("/api/users")
public interface UserApi {

    @Operation(summary = "구글 로그인", description = "Front-end로부터 id_token을 받아서 Poomy Service 회원가입 및 로그인을 진행한다.")
    @ApiResponse(responseCode = "200", description = "OK", headers = {@Header(name = "accessToken", description = "유저 권한을 위한 jwt 토큰")})
    @ApiResponse(responseCode = "401", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ApiErrorResult.class)))
    @PostMapping("/login/google")
    ResponseEntity<ApiResult<?>> loginGoogle(@Valid @RequestBody LoginGoogleReqDto loginGoogleReqDto);

    @Operation(summary = "Poomy 로그인", description = "개발할 때, jwt 토큰 발급용으로 만들어졌다. 이미 구글로 로그인한 경우 한정")
    @ApiResponse(responseCode = "200", description = "OK", headers = {@Header(name = "accessToken", description = "유저 권한을 위한 jwt 토큰")})
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ApiErrorResult.class)))
    @PostMapping("/login/poomy")
    ResponseEntity<ApiResult<UserResDto>> loginPoomy(@Valid @RequestBody LoginPoomyReqDto loginGoogleReqDto);

    @Operation(summary = "닉네임이 등록", description = "닉네임 중복 여부를 검사하여 등록이 되지 않을 경우 등록한다.")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ApiErrorResult.class)))
    @GetMapping("/register")
    ResponseEntity<ApiResult<UserResDto>> registerNickName(@Valid @ModelAttribute RegisterNickNameReqDto registerNickNameReqDto);
}
