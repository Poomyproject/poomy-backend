package com.poomy.mainserver.home.dto.res;

import com.poomy.mainserver.spot.entity.Spot;
import lombok.Builder;

@Builder
public record SpotsRes(Long id, String name) {

    public static SpotsRes of(Spot spot) {
        return SpotsRes.builder()
                .id(spot.getId())
                .name(spot.getName())
                .build();
    }
}
