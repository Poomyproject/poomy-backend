package com.poomy.mainserver.search.dto;

import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.search.entity.Search;
import lombok.Builder;

@Builder

public record SearchCountResDto(Long id, String name, Long count) {
    public static SearchCountResDto of(Shop shop, Search search){
        return SearchCountResDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .count(search.getCount())
                .build();
    }
}