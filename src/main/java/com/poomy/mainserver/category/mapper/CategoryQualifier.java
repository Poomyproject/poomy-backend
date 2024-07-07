package com.poomy.mainserver.category.mapper;

import com.poomy.mainserver.category.type.AtmosphereType;
import com.poomy.mainserver.category.type.HotPlaceType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class CategoryQualifier {

    @Named("AtmosphereTypeToName")
    public String atmosphereTypeToName(AtmosphereType atmosphereType){
        return atmosphereType.getName();
    }

    @Named("HotPlaceTypeToName")
    public String hotPlaceTypeToName(HotPlaceType hotPlaceType){
        return hotPlaceType.getName();
    }

}
