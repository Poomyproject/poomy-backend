package com.poomy.mainserver.user.mapper;

import com.poomy.mainserver.user.dto.UserResDto;
import com.poomy.mainserver.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper{

    UserResDto toUserResDto(User user);

}
