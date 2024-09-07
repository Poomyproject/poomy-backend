package com.poomy.mainserver.detail.service;

import com.poomy.mainserver.detail.dto.res.ShopDetailRes;
import com.poomy.mainserver.favorite.repository.FavoriteRepository;
import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.home.entity.ShopImage;
import com.poomy.mainserver.home.repository.ShopImageRepository;
import com.poomy.mainserver.home.repository.ShopRepository;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopDetailService {

    private final ShopRepository shopRepository;
    private final ShopImageRepository shopImageRepository;
    private final FavoriteRepository favoriteRepository;

    public ShopDetailRes getShopDetail(Long shopId, User user) {

        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new CommonException(BError.NOT_EXIST, shopId + " of shopId"));

        Boolean isFavorite = favoriteRepository.existsByUserAndShop(user, shop);

        List<ShopImage> shopImageList = shopImageRepository.findShopImagesByShop_Id(shopId);

        return shop.toDto(isFavorite, shopImageList);
    }


}
