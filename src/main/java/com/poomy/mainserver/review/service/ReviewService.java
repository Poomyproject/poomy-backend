package com.poomy.mainserver.review.service;

import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.home.repository.ShopRepository;
import com.poomy.mainserver.review.dto.req.GetReviewReqDto;
import com.poomy.mainserver.review.dto.res.GetReviewImageResDto;
import com.poomy.mainserver.review.dto.res.GetReviewResDto;
import com.poomy.mainserver.review.dto.res.ReviewImageResDto;
import com.poomy.mainserver.review.dto.res.ReviewResDto;
import com.poomy.mainserver.review.entity.Review;
import com.poomy.mainserver.review.entity.ReviewImage;
import com.poomy.mainserver.review.repository.ReviewImageRepository;
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
    private final ReviewImageRepository reviewImageRepository;

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
                .skip((long) (page - 1) *limit)
                .limit(limit)
                .map(Review::toReviewResDto)
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

    public GetReviewImageResDto getReviewImages(GetReviewReqDto reqDto) {
        int page = reqDto.getPage();
        int limit = reqDto.getLimit();
        Shop shop = getShopById(reqDto.getPoomShopId());
        List<Review> reviews = reviewRepository.findAllByShop(shop);
        List<ReviewImage> reviewImages = reviewImageRepository.findAllByReviewIn(reviews);
        List<ReviewImageResDto> reviewImageResDtos = reviewImages.stream()
                .sorted(Comparator.comparing(ReviewImage::getCreatedAt).reversed())
                .skip((long) (page - 1) *limit)
                .limit(limit)
                .map(ReviewImage::toReviewImageResDto)
                .toList();
        return GetReviewImageResDto.builder()
                .totalImgUrl(reviewImages.size())
                .page(page)
                .imgUrls(reviewImageResDtos)
                .build();
    }

    private Shop getShopById(Long poomShopId){
        return shopRepository.findById(poomShopId)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, poomShopId + " of poomShopId"));
    }

    public ReviewResDto getReviewByReviewId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, reviewId + " of reviewId"));
        return review.toReviewResDto();
    }

    public ReviewResDto getReviewByReviewImgId(Long reviewImgId) {
        ReviewImage reviewImage = reviewImageRepository.findById(reviewImgId)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, reviewImgId + " of reviewImgId"));
        return reviewImage.getReview().toReviewResDto();
    }
}
