package com.poomy.mainserver.user.repository;

import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserSpotRepository extends JpaRepository<UserSpot, Long> {
    List<UserSpot> findUserSpotsByUser(User user);
}
