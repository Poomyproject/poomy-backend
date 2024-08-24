package com.poomy.mainserver.user.dto;

import com.poomy.mainserver.mood.dto.MoodNmResDto;
import com.poomy.mainserver.spot.dto.SpotNmResDto;
import com.poomy.mainserver.spot.dto.SpotResDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserInfoResDto {

    private String nickname;
    private String googleEmail;
    private String imgUrl;
    private List<MoodNmResDto> moods;
    private List<SpotNmResDto> spots;

}
