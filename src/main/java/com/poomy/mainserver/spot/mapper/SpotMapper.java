package com.poomy.mainserver.spot.mapper;

import com.poomy.mainserver.spot.dto.SpotResDto;
import com.poomy.mainserver.spot.entity.Spot;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SpotMapper {

    SpotResDto toSpotResDto(Spot spot);

}
