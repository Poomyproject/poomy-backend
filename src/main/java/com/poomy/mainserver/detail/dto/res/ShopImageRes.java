package com.poomy.mainserver.detail.dto.res;

import com.poomy.mainserver.home.entity.ShopImage;
import lombok.Builder;

@Builder
public record ShopImageRes(Long id, String url) {

    public static ShopImageRes ofShopImage(ShopImage shopImage) {
        return ShopImageRes.builder()
                .id(shopImage.getId())
                .url(shopImage.getUrl()).build();
    }
}
