package com.poomy.mainserver.review.service;

import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.home.repository.ShopRepository;
import com.poomy.mainserver.review.dto.req.GetReviewReqDto;
import com.poomy.mainserver.review.dto.res.GetReviewResDto;
import com.poomy.mainserver.review.dto.res.ReviewImageResDto;
import com.poomy.mainserver.review.dto.res.ReviewResDto;
import com.poomy.mainserver.review.entity.Review;
import com.poomy.mainserver.review.entity.ReviewImage;
import com.poomy.mainserver.review.repository.ReviewRepository;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private final ShopRepository shopRepository;
    private final ReviewRepository reviewRepository;

    public GetReviewResDto getReviews(GetReviewReqDto reqDto) {
        int page = reqDto.getPage();
        int limit = reqDto.getLimit();
        Shop shop = getShopById(reqDto.getPoomShopId());
        List<Review> reviews = reviewRepository.findAllByShop(shop);
        int totalReview = reviews.size();
        List<ReviewImage> reviewImages = reviews.stream()
                .flatMap(review -> review.getReviewImages().stream())
                .sorted(Comparator.comparing(ReviewImage::getCreatedAt).reversed())
                .toList();
        int totalImgUrl = reviewImages.size();
        List<ReviewImageResDto> imgUrls = reviewImages.stream()
                .limit(3)
                .map(ReviewImage::toReviewImageResDto)
                .toList();
        int totalRecommend = reviews.stream().filter(Review::getIsRecommend).toList().size();
        List<ReviewResDto> reviewResDtos = reviews.stream()
                .map(Review::toReviewResDto)
                .skip((long) (page - 1) *limit)
                .limit(limit)
                .toList();
        return GetReviewResDto.builder()
                .totalRecommend(totalRecommend)
                .imgUrls(imgUrls)
                .totalImgUrl(totalImgUrl)
                .totalReview(totalReview)
                .page(page)
                .reviews(reviewResDtos)
                .build();
    }

    private Shop getShopById(Long poomShopId){
        return shopRepository.findById(poomShopId)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, poomShopId + " of poomShopId"));
    }

}
