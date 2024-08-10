package com.poomy.mainserver.mood.repository;

import com.poomy.mainserver.mood.entity.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MoodRepository extends JpaRepository<Mood, Integer> {

    @Query(value = "select * from moods order by RAND() limit 1", nativeQuery = true)
    Mood getRandomMood();
}
