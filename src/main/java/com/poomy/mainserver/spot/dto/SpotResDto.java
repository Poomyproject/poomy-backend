package com.poomy.mainserver.spot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SpotResDto {

    private Integer id;
    private String name;
    private String imgUrl;

}
