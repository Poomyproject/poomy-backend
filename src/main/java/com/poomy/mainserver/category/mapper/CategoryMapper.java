package com.poomy.mainserver.category.mapper;

import com.poomy.mainserver.category.dto.CategoryResDto;
import com.poomy.mainserver.category.entity.Atmosphere;
import com.poomy.mainserver.category.entity.HotPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        uses = CategoryQualifier.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = {"AtmosphereTypeToName"})
    CategoryResDto toCategoryResDto(Atmosphere atmosphere);

    @Mapping(source = "name", target = "name", qualifiedByName = {"HotPlaceTypeToName"})
    CategoryResDto toCategoryResDto(HotPlace hotPlace);

}
