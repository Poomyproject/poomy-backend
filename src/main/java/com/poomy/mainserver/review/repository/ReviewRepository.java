package com.poomy.mainserver.review.repository;

import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByShop(Shop shop);

}
