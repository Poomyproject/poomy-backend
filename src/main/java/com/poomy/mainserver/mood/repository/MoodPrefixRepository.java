package com.poomy.mainserver.mood.repository;

import com.poomy.mainserver.mood.entity.MoodPrefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoodPrefixRepository extends JpaRepository<MoodPrefix, Long> {

    @Query(value = "select * from moods_prefix where moods_prefix.mood_id = :moodId ORDER BY RAND() LIMIT 1", nativeQuery = true)
    MoodPrefix findMoodPrefixById(@Param("moodId") Long moodId);
}
