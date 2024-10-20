package com.poomy.mainserver.mood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MoodResDto {

    private Long id;
    private String name;
    private String imgUrl;

}
