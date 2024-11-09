package com.poomy.mainserver.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetReviewResDto {

    private int totalRecommend;

    private List<ReviewImageResDto> imgUrls;

    private int totalImgUrl;

    private int totalReview;

    private int page;

    private List<ReviewResDto> reviews;

}
