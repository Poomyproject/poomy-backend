package com.poomy.mainserver.mood.repository;

import com.poomy.mainserver.mood.entity.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<Mood, Integer> {
}
