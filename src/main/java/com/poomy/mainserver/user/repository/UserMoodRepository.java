package com.poomy.mainserver.user.repository;

import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserMood;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserMoodRepository extends JpaRepository<UserMood, Long> {
    List<UserMood> findUserMoodsByUser(User user);

}
