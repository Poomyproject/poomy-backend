package com.poomy.mainserver.user.repository;

import com.poomy.mainserver.user.entity.UserMood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMoodRepository extends JpaRepository<UserMood, Long> {
}
