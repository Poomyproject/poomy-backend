package com.poomy.mainserver.search.repository;

import com.poomy.mainserver.search.entity.Search;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {

    @Transactional
    @Query(value = "update search set count = count+1 where shop_id = :shopId", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    int increaseSearchCountById(Long shopId);

    @Transactional
    @Query(value = "insert into search (count, shop_id) values(0, :shopId)", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    int insertSearchCountById(Long shopId);

    @Query(value = "select * from search where shop_id= :shopId", nativeQuery = true)
    Search getSearchCountByShopId(@Param("shopId") Long shopId);

    @Query(value = "select * from search order by count DESC limit 5", nativeQuery = true)
    List<Search> getTopFiveShops();
}
