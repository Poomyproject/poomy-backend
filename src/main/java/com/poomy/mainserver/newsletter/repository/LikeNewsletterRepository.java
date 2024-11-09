package com.poomy.mainserver.newsletter.repository;

import com.poomy.mainserver.newsletter.entity.LikeNewsletter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikeNewsletterRepository extends JpaRepository<LikeNewsletter,Long> {
    @Transactional
    @Query(value = "insert into news_letter_users (user_id, newsletter_id, is_like) values(:userId, :newsletterId, true)", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void insertUserToLikeNewsletter(Integer userId, Long newsletterId);

    @Transactional
    @Query(value = "update news_letter_users set is_like = true where user_id= :userId and newsletter_id= :newsletterId", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    int updateLikeUserFeedbackById(Integer userId, Long newsletterId);

    @Transactional
    @Query(value = "update news_letter_users set is_like = false where user_id= :userId and newsletter_id= :newsletterId", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    int updateUnlikeUserFeedbackById(Integer userId, Long newsletterId);

    @Query(value = "select * from news_letter_users where user_id= :userId and newsletter_id= :newsletterId", nativeQuery = true)
    LikeNewsletter getLikeNewsletter(Integer userId, Long newsletterId);


}
