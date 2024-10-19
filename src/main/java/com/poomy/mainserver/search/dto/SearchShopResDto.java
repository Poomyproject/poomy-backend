package com.poomy.mainserver.search.dto;

import com.poomy.mainserver.home.entity.Shop;
import lombok.Builder;

@Builder
public record SearchShopResDto(Long id, String name, String spot, String mood, Double latitude, Double longitude, String image, Boolean isFavorite) {
    public static SearchShopResDto ofSearchShopByName(Shop shop, String image, Boolean isFavorite){
        return SearchShopResDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .spot(shop.getSpot().getName())
                .mood(shop.getMood().getName())
                .latitude(shop.getLatitude())
                .longitude(shop.getLongitude())
                .image(image)
                .isFavorite(isFavorite)
                .build();
    }
}
