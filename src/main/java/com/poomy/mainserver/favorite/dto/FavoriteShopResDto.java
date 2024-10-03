package com.poomy.mainserver.favorite.dto;

import com.poomy.mainserver.favorite.entity.Favorite;
import lombok.Builder;

@Builder
public record FavoriteShopResDto(Long id, Long shopId, String shopName, String spot, String mood, Double longitude, Double latitude, String image, Boolean isFavorite) {
    public static FavoriteShopResDto ofFavoriteShop(Favorite favorite, String image){
        return FavoriteShopResDto.builder()
                .id(favorite.getId())
                .shopId(favorite.getShop().getId())
                .shopName(favorite.getShop().getName())
                .spot(favorite.getShop().getSpot().getName())
                .mood(favorite.getShop().getMood().getName())
                .longitude(favorite.getShop().getLongitude())
                .latitude(favorite.getShop().getLatitude())
                .image(image)
                .isFavorite(favorite.getIsFavorite())
                .build();
    }
}
