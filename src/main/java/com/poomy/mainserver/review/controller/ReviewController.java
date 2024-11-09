package com.poomy.mainserver.review.controller;

import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.service.MoodService;
import com.poomy.mainserver.review.api.ReviewApi;
import com.poomy.mainserver.review.dto.req.GetReviewReqDto;
import com.poomy.mainserver.review.dto.req.RegisterReviewReqDto;
import com.poomy.mainserver.review.dto.res.GetReviewImageResDto;
import com.poomy.mainserver.review.dto.res.GetReviewResDto;
import com.poomy.mainserver.review.dto.res.ReviewResDto;
import com.poomy.mainserver.review.service.ReviewService;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class ReviewController implements ReviewApi {

    private final ReviewService reviewService;
    private final UserService userService;

    @Override
    public ResponseEntity<ApiResult<GetReviewResDto>> getReviews(GetReviewReqDto reqDto) {
        GetReviewResDto getReviewResDto = reviewService.getReviews(reqDto);
        return ResponseEntity.ok(ApiUtils.success(getReviewResDto));
    }

    @Override
    public ResponseEntity<ApiResult<GetReviewImageResDto>> getReviewImages(GetReviewReqDto reqDto) {
        GetReviewImageResDto reviewImageResDtos = reviewService.getReviewImages(reqDto);
        return ResponseEntity.ok(ApiUtils.success(reviewImageResDtos));
    }

    @Override
    public ResponseEntity<ApiResult<ReviewResDto>> getReviewByReviewId(Long reviewId) {
        ReviewResDto reviewResDto = reviewService.getReviewByReviewId(reviewId);
        return ResponseEntity.ok(ApiUtils.success(reviewResDto));
    }

    @Override
    public ResponseEntity<ApiResult<ReviewResDto>> getReviewByReviewImgId(Long reviewImgId) {
        ReviewResDto reviewResDto = reviewService.getReviewByReviewImgId(reviewImgId);
        return ResponseEntity.ok(ApiUtils.success(reviewResDto));
    }

    @Override
    public ResponseEntity<ApiResult<ReviewResDto>> registerReview(RegisterReviewReqDto reqDto) {
        User user = userService.getUser();
        ReviewResDto reviewResDto = reviewService.registerReview(user, reqDto);
        return ResponseEntity.created(null).body(ApiUtils.success(reviewResDto));
    }
}
