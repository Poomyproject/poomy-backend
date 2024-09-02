package com.poomy.mainserver.review.api;

import com.poomy.mainserver.review.dto.req.GetReviewReqDto;
import com.poomy.mainserver.review.dto.res.GetReviewImageResDto;
import com.poomy.mainserver.review.dto.res.GetReviewResDto;
import com.poomy.mainserver.review.dto.res.ReviewImageResDto;
import com.poomy.mainserver.util.api.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Review API", description = "Review 관련된 API")
@RequestMapping("/api/reviews")
public interface ReviewApi {

    @Operation(summary = "리뷰 조회", description = "무한 스크롤을 고려한 리뷰 조회")
    @GetMapping("")
    ResponseEntity<ApiResult<GetReviewResDto>> getReviews(
            @Valid @ModelAttribute GetReviewReqDto reqDto
    );

    @Operation(summary = "리뷰 사진 전체 조회", description = "무한 스크롤을 고려한 리뷰 조회")
    @GetMapping("/imgs")
    ResponseEntity<ApiResult<GetReviewImageResDto>> getReviewImages(
            @Valid @ModelAttribute GetReviewReqDto reqDto
    );



}
