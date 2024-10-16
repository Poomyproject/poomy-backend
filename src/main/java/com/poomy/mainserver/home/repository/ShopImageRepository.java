package com.poomy.mainserver.home.repository;

import com.poomy.mainserver.home.entity.ShopImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopImageRepository extends JpaRepository<ShopImage, Long> {

    Optional<ShopImage> findTop1ByShop_Id(Long shopId);

    Optional<List<ShopImage>> findShopImagesByShop_Id(Long shopId);
}
