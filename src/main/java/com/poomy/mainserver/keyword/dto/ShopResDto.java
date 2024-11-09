package com.poomy.mainserver.keyword.dto;

import com.poomy.mainserver.detail.dto.res.ShopImageRes;
import com.poomy.mainserver.home.dto.res.ShopByMoodRes;
import com.poomy.mainserver.home.entity.Shop;
import lombok.Builder;

@Builder
public record ShopResDto(Long id, String name, String spot, String mood, String location, String image, Boolean isFavorite) {
    public static ShopResDto ofShop(Shop shop, String image, Boolean isFavorite) {
        return ShopResDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .spot(shop.getSpot().getName())
                .mood(shop.getMood().getName())
                .location(shop.getLocation())
                .image(image)
                .isFavorite(isFavorite)
                .build();
    }
}
