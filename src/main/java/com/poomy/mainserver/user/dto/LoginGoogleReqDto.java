package com.poomy.mainserver.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginGoogleReqDto {

    @NotBlank(message = "id Token을 넘겨주세요")
    private String idToken;

}
