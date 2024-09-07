package com.poomy.mainserver.detail.dto.res;

import com.poomy.mainserver.home.entity.ShopImage;
import lombok.Builder;

import java.util.List;

@Builder
public record ShopDetailRes(Long shopId, String name, String phoneNumber, String location, String nearbyStation,
                            Double latitude, Double longitude, String mood, String spot, List<ShopImage> shopImageList,
                            Boolean favorite, String openingHours) {
}
