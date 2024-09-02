package com.poomy.mainserver.review.repository;

import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.review.entity.Review;
import com.poomy.mainserver.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    List<ReviewImage> findAllByReviewIn(List<Review> reviews);

}
