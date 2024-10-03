package com.poomy.mainserver.favorite.dto;

import com.poomy.mainserver.favorite.entity.Favorite;
import lombok.Builder;

@Builder
public record LikeShopResDto(Long id, Boolean isFavorite) {
    public static LikeShopResDto of(Favorite favorite){
        return LikeShopResDto
                .builder()
                .id(favorite.getId())
                .isFavorite(favorite.getIsFavorite())
                .build();
    }
}
