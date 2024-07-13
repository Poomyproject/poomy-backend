package com.poomy.mainserver.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterNicknameReqDto {

    @Pattern(regexp = "[가-힣]+", message = "닉네임은 한글만 입력 가능합니다")
    @Size(min = 1, max = 5, message = "닉네임은 최대 5자까지 입력 가능합니다")
    private String nickname;

}
