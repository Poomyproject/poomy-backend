package com.poomy.mainserver.spot.repository;

import com.poomy.mainserver.spot.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    @Query(value = "select * from spots ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Spot> findSpots();
}
