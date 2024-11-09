package com.poomy.mainserver.home.repository;

import com.poomy.mainserver.home.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Long> {

    @Query(value = "select * from poom_shops where poom_shops.spot_id = :spotId ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Shop> findShopsBySpot(@Param("spotId") Long spotId);

    @Query(value = "select * from poom_shops where poom_shops.mood_id = :moodId order by RAND() limit 6", nativeQuery = true)
    List<Shop> findShopsByMood(@Param("moodId") Long moodId);

    @Query(value = "select * from poom_shops where name like :word% ORDER BY name", nativeQuery = true)
    List<Shop> findFirstShopsByName(@Param("word") String word);

    @Query(value = "select * from poom_shops where name like CONCAT('_','%', :word, '%') ORDER BY name", nativeQuery = true)
    List<Shop> findSecondShopsByName(@Param("word") String word);

    @Query(value = "select * from poom_shops where id = :shopId", nativeQuery = true)
    Shop findShopById(@Param("shopId") Long shopId);

    @Query(value = "SELECT * FROM poom_shops ORDER BY RAND() LIMIT :remainingCount", nativeQuery = true)
    List<Shop> findRandomRemainingCount(int remainingCount);

    @Query(value = "SELECT * FROM poom_shops WHERE spot_id = :spotId", nativeQuery = true)
    List<Shop> findBySpotId(Long spotId);

    @Query(value = "SELECT * FROM poom_shops WHERE mood_id = :moodId", nativeQuery = true)
    List<Shop> findByMoodId(Long moodId);

    @Query(value = "SELECT * FROM poom_shops WHERE mood_id = :moodId and spot_id = :spotId", nativeQuery = true)
    List<Shop> findByMoodIdAndSpotId(Long moodId, Long spotId);
}
