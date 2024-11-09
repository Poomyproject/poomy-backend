package com.poomy.mainserver.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewResDto {

    private Long id;

    private String userNickName;

    private String userImgUrl;

    private String date;

    private String content;

    private Boolean isRecommend;

    private List<ReviewImageResDto> imgUrls;

}
