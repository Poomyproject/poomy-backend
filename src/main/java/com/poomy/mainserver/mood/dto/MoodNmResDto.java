package com.poomy.mainserver.mood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MoodNmResDto {
    private Long id;
    private String name;
}
