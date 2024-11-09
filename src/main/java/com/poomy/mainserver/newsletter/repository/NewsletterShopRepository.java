package com.poomy.mainserver.newsletter.repository;

import com.poomy.mainserver.newsletter.entity.NewsletterShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsletterShopRepository extends JpaRepository<NewsletterShop,Long> {

    @Query(value = "select * from news_letter_shops where shop_id= :shopId", nativeQuery = true)
    NewsletterShop getNewsletterShopById(Long shopId);
}
