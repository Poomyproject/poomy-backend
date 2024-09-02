package com.poomy.mainserver.review.controller;

import com.poomy.mainserver.review.api.ReviewApi;
import com.poomy.mainserver.review.dto.req.GetReviewReqDto;
import com.poomy.mainserver.review.dto.res.GetReviewResDto;
import com.poomy.mainserver.review.service.ReviewService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class ReviewController implements ReviewApi {

    private final ReviewService reviewService;

    @Override
    public ResponseEntity<ApiResult<GetReviewResDto>> getReviews(GetReviewReqDto reqDto) {
        GetReviewResDto getReviewResDto = reviewService.getReviews(reqDto);
        return ResponseEntity.ok(ApiUtils.success(getReviewResDto));
    }
}
