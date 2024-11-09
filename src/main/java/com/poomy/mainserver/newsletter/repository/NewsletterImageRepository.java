package com.poomy.mainserver.newsletter.repository;

import com.poomy.mainserver.newsletter.entity.NewsletterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsletterImageRepository extends JpaRepository<NewsletterImage, Long> {
    NewsletterImage findNewsletterImageByNewsletterId(Long newsletterId);

    @Query(value = "select * from news_letter_images where id = :newsletterId", nativeQuery = true)
    NewsletterImage getNewsletterImageById(Long newsletterId);
}
