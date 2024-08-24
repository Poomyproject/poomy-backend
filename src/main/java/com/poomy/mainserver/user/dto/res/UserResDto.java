package com.poomy.mainserver.user.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResDto {

    private String googleEmail;
    private String nickname;

}
