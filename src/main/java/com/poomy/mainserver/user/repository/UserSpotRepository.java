package com.poomy.mainserver.user.repository;

import com.poomy.mainserver.user.entity.UserSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSpotRepository extends JpaRepository<UserSpot, Long> {
}
