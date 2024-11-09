package com.poomy.mainserver.search.dto;

import com.poomy.mainserver.home.entity.Shop;
import lombok.Builder;

@Builder
public record TopFiveShopResDto(Long id, String name) {
    public static TopFiveShopResDto ofTopFiveShop(Shop shop) {
        return TopFiveShopResDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .build();
    }
}
