package com.poomy.mainserver.detail.dto.res;

import lombok.Builder;

import java.util.List;

@Builder
public record ShopDetailRes(Long shopId, String name, String phoneNumber, String location, String nearbyStation,
                            Double latitude, Double longitude, String mood, String spot,
                            List<ShopImageRes> shopImageList,
                            Boolean favorite, String openingHours) {
}
