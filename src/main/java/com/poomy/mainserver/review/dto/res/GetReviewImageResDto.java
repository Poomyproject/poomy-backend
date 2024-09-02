package com.poomy.mainserver.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetReviewImageResDto {

    private int totalImgUrl;
    private int page;
    private List<ReviewImageResDto> imgUrls;

}
