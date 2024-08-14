package com.poomy.mainserver.home.dto.res;

import lombok.Builder;

import java.util.List;

@Builder
public record HomeShopRes(Long id, String prefix, String hashtag, List<?> shopList) {
}
