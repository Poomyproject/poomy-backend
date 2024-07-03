package com.poomy.mainserver.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResDto {

    private String googleEmail;
    private String nickName;

}
