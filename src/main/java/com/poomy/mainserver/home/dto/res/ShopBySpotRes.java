package com.poomy.mainserver.home.dto.res;

import com.poomy.mainserver.home.entity.Shop;
import lombok.Builder;

@Builder
public record ShopBySpotRes(Long id, String name, String mood, String image, int favoriteNum) {
    public static ShopBySpotRes ofShopBySpot(Shop shop, String image, int favoriteNum) {
        return ShopBySpotRes.builder()
                .id(shop.getId())
                .name(shop.getName())
                .mood(shop.getMood().getName())
                .image(image)
                .favoriteNum(favoriteNum)
                .build();
    }
}
