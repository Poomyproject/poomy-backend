package com.poomy.mainserver.spot.repository;

import com.poomy.mainserver.spot.entity.SpotPrefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpotPrefixRepository extends JpaRepository<SpotPrefix, Long> {

    @Query(value = "select * from spots_prefix ORDER BY RAND() LIMIT 1", nativeQuery = true)
    SpotPrefix findSpotPrefixBy();

}
