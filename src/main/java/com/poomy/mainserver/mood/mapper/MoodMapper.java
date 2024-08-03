package com.poomy.mainserver.mood.mapper;

import com.poomy.mainserver.mood.dto.MoodResDto;
import com.poomy.mainserver.mood.entity.Mood;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MoodMapper {

    MoodResDto toMoodResDto(Mood mood);

}
