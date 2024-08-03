package com.poomy.mainserver.spot.repository;

import com.poomy.mainserver.spot.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Integer> {
}
