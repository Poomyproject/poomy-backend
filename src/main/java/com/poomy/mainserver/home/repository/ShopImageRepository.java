package com.poomy.mainserver.home.repository;

import com.poomy.mainserver.home.entity.ShopImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopImageRepository extends JpaRepository<ShopImage,Long> {

    ShopImage findShopImageByShop_Id(Long ShopId);
}
