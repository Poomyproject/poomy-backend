package com.poomy.mainserver.home.repository;

import com.poomy.mainserver.home.entity.ShopImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopImageRepository extends JpaRepository<ShopImage, Long> {

    ShopImage findShopImageByShop_Id(Long shopId);

    List<ShopImage> findShopImagesByShop_Id(Long shopId);
}
