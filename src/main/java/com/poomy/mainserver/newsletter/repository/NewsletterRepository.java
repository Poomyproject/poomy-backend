package com.poomy.mainserver.newsletter.repository;

import com.poomy.mainserver.newsletter.entity.Newsletter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsletterRepository extends JpaRepository<Newsletter,Long> {

    @Query(value = "select * from news_letter order by RAND() limit 3", nativeQuery = true)
    List<Newsletter> getRandomNewsletters();

    @Query(value = "select * from news_letter order by id desc", nativeQuery = true)
    List<Newsletter> getAllNewNewsLetter();

    @Query(value = "select * from news_letter order by id", nativeQuery = true)
    List<Newsletter> getAllOldNewsLetter();

    @Query(value = "select * from news_letter order by user_feedback desc", nativeQuery = true)
    List<Newsletter> getAllHotNewsLetter();

    @Query(value = "select * from news_letter where id = :newsletterId", nativeQuery = true)
    Newsletter getNewsletterById(Long newsletterId);

    @Transactional
    @Query(value = "update news_letter set news_letter.user_feedback = news_letter.user_feedback+1 where news_letter.id = :newsletterId", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    int increaseUserFeedbackById(Long newsletterId);

    @Transactional
    @Query(value = "update news_letter set news_letter.user_feedback = news_letter.user_feedback-1 where news_letter.id = :newsletterId", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    int decreaseUserFeedbackById(Long newsletterId);
}
