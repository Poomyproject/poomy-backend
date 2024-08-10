package com.poomy.mainserver.newsletter.repository;

import com.poomy.mainserver.newsletter.entity.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsletterRepository extends JpaRepository<Newsletter,Long> {

    @Query(value = "select * from news_letter order by RAND() limit 3", nativeQuery = true)
    List<Newsletter> getRandomNewsletters();
}
