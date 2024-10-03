package com.poomy.mainserver.home.repository;

import com.poomy.mainserver.home.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Long> {


    @Query(value = "select * from poom_shops where poom_shops.spot_id = :spotId ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Shop> findShopsBySpot(@Param("spotId") Long spotId);


    @Query(value = "select * from poom_shops where poom_shops.mood_id = :moodId order by RAND() limit 6", nativeQuery = true)
    List<Shop> findShopsByMood(@Param("moodId") Long moodId);

    @Query(value = "select * from poom_shops where poom_shops.mood_id = :moodId and spot_id= :spotId and id= :shopId", nativeQuery = true)
    Shop getFilteredShops(Long moodId, Long spotId, Long shopId);

    @Query(value = "select * from poom_shops where poom_shops.mood_id = :moodId and id= :shopId", nativeQuery = true)
    Shop getFilteredShopsByMood(Long moodId, Long shopId);

    @Query(value = "select * from poom_shops where poom_shops.spot_id= :spotId and id= :shopId", nativeQuery = true)
    Shop getFilteredShopsBySpot(Long spotId, Long shopId);
}
