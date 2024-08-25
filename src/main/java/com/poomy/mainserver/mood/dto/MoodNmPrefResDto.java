package com.poomy.mainserver.mood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MoodNmPrefResDto {
    private Long id;
    private String name;
    @Setter
    private Boolean isPreferred;
}
