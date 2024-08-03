package com.poomy.mainserver.user.mapper;

import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.user.dto.UserMoodResDto;
import com.poomy.mainserver.user.dto.UserSpotResDto;
import com.poomy.mainserver.user.dto.UserResDto;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserMood;
import com.poomy.mainserver.user.entity.UserSpot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        uses = UserQualifier.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper{

    UserResDto toUserResDto(User user);

    @Mapping(source = "user", target = "nickname", qualifiedByName = {"GetNickname"})
    @Mapping(source = "mood", target = "mood", qualifiedByName = {"GetMoodName"})
    UserMoodResDto toUserMoodResDto(UserMood userMood);

    @Mapping(source = "user", target = "nickname", qualifiedByName = {"GetNickname"})
    @Mapping(source = "spot", target = "spot", qualifiedByName = {"GetSpotName"})
    UserSpotResDto toUserSpotResDto(UserSpot userSpot);

    @Mapping(target = "id", ignore = true)
    UserMood toUserMood(User user, Mood mood);

    @Mapping(target = "id", ignore = true)
    UserSpot toUserSpot(User user, Spot spot);
}