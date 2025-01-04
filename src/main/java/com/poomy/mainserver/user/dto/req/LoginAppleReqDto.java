package com.poomy.mainserver.user.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginAppleReqDto {

    @NotBlank(message = "idToken을 넘겨주세요")
    private String idToken;

    @NotBlank(message = "authorizationCode를 넘겨주세요") // 추가
    private String authorizationCode;
}