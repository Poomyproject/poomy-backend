package com.poomy.mainserver.home.dto.res;

import com.poomy.mainserver.home.entity.Shop;
import lombok.Builder;

@Builder
public record ShopByMoodRes(Long id, String name, String spot, String image) {
    public static ShopByMoodRes ofShopByMood(Shop shop, String image) {
        return ShopByMoodRes.builder()
                .id(shop.getId())
                .name(shop.getName())
                .spot(shop.getSpot().getName())
                .image(image)
                .build();
    }
}
