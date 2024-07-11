package com.poomy.mainserver.user.mapper;

import com.poomy.mainserver.category.entity.Atmosphere;
import com.poomy.mainserver.category.entity.HotPlace;
import com.poomy.mainserver.user.entity.User;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class UserQualifier {

    @Named("GetNickName")
    public String getNickName(User user){
        return user.getNickName();
    }

    @Named("GetAtmosphereName")
    public String getAtmosphereName(Atmosphere atmosphere){
        return atmosphere.getName().getName();
    }

    @Named("GetHotPlaceName")
    public String getHotPlaceName(HotPlace hotPlace){
        return hotPlace.getName().getName();
    }

}
