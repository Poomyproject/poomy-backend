package com.poomy.mainserver.category.mapper;

import com.poomy.mainserver.category.type.AtmosphereType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class AtmosphereQualifier {

    @Named("AtmosphereTypeToName")
    public String atmosphereTypeToName(AtmosphereType atmosphereType){
        return atmosphereType.getName();
    }

}
