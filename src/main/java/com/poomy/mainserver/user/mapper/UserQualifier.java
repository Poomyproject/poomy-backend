package com.poomy.mainserver.user.mapper;

import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.user.entity.User;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class UserQualifier {

    @Named("GetNickname")
    public String getNickName(User user){
        return user.getNickname();
    }

    @Named("GetMoodName")
    public String getMoodName(Mood mood){
        return mood.getName();
    }

    @Named("GetSpotName")
    public String getHotPlaceName(Spot spot){
        return spot.getName();
    }

}
