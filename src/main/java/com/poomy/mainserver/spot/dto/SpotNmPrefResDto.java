package com.poomy.mainserver.spot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SpotNmPrefResDto {

    private Long id;
    private String name;
    @Setter
    private Boolean isPreferred;

}
