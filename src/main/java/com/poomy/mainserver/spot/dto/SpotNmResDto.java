package com.poomy.mainserver.spot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SpotNmResDto {

    private Long id;
    private String name;

}
