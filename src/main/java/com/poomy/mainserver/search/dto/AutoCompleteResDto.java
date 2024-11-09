package com.poomy.mainserver.search.dto;

import com.poomy.mainserver.home.entity.Shop;
import lombok.Builder;

@Builder
public record AutoCompleteResDto(Long id, String name) {
    public static AutoCompleteResDto of(Shop shop){
        return AutoCompleteResDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .build();
    }
}
