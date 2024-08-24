package com.poomy.mainserver.user.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserSpotResDto {

    private String nickname;
    private String spot;

}
