package com.poomy.mainserver.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserHotPlaceResDto {

    private String nickName;
    private String hotPlace;

}
