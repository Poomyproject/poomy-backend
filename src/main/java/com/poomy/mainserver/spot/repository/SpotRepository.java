package com.poomy.mainserver.spot.repository;

import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.spot.entity.Spot;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    @Query(value = "select * from spots ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Spot> findSpots();

    @Query(value = "select * from spots where id = :spotId", nativeQuery = true)
    Spot getSpot(Long spotId);
}
