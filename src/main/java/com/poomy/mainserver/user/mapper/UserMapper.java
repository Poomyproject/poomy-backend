package com.poomy.mainserver.user.mapper;

import com.poomy.mainserver.category.entity.Atmosphere;
import com.poomy.mainserver.category.entity.HotPlace;
import com.poomy.mainserver.user.dto.UserAtmosphereResDto;
import com.poomy.mainserver.user.dto.UserHotPlaceResDto;
import com.poomy.mainserver.user.dto.UserResDto;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserAtmosphere;
import com.poomy.mainserver.user.entity.UserHotPlace;
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
    @Mapping(source = "atmosphere", target = "atmosphere", qualifiedByName = {"GetAtmosphereName"})
    UserAtmosphereResDto toUserAtmosphereResDto(UserAtmosphere userAtmosphere);

    @Mapping(source = "user", target = "nickname", qualifiedByName = {"GetNickname"})
    @Mapping(source = "hotPlace", target = "hotPlace", qualifiedByName = {"GetHotPlaceName"})
    UserHotPlaceResDto toUserHotPlaceResDto(UserHotPlace userHotPlace);

    @Mapping(target = "id", ignore = true)
    UserAtmosphere toUserAtmosphere(User user, Atmosphere atmosphere);

    @Mapping(target = "id", ignore = true)
    UserHotPlace toUserHotPlace(User user, HotPlace hotPlace);
}