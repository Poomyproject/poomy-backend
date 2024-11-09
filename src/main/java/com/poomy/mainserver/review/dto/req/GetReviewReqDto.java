package com.poomy.mainserver.review.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetReviewReqDto {

    @NotNull
    @Min(value = 1, message = "page는 1 이상이어야 합니다.")
    private int page;

    @NotNull
    @Min(value = 1, message = "limit는 1 이상이어야 합니다.")
    private int limit;

    @NotNull
    private Long poomShopId;

}
