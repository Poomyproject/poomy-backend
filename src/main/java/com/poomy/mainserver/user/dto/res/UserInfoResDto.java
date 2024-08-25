package com.poomy.mainserver.user.dto.res;

import com.poomy.mainserver.mood.dto.MoodNmResDto;
import com.poomy.mainserver.spot.dto.SpotNmResDto;
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
