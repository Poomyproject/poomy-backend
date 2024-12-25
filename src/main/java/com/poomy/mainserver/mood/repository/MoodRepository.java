package com.poomy.mainserver.mood.repository;

import com.poomy.mainserver.mood.entity.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoodRepository extends JpaRepository<Mood, Long> {

    @Query(value = "select * from moods order by RAND() limit 1", nativeQuery = true)
    Mood getRandomMood();

    @Query(value = "select * from moods where id = :moodId", nativeQuery = true)
    Mood getMood(Long moodId);
}
