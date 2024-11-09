package com.poomy.mainserver.keyword.service;


import com.poomy.mainserver.detail.dto.res.ShopImageRes;
import com.poomy.mainserver.favorite.dto.FavoriteShopResDto;
import com.poomy.mainserver.favorite.entity.Favorite;
import com.poomy.mainserver.favorite.repository.FavoriteRepository;
import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.home.entity.ShopImage;
import com.poomy.mainserver.home.repository.ShopImageRepository;
import com.poomy.mainserver.home.repository.ShopRepository;
import com.poomy.mainserver.keyword.dto.ShopResDto;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final ShopRepository shopRepository;
    private final FavoriteRepository favoriteRepository;
    private final ShopImageRepository shopImageRepository;
    private final UserService userService;


    public List<ShopResDto> getShopList() {
        User user = userService.getUser();

        List<ShopResDto> shopResDtos = shopRepository.findAll().stream().map(shop -> {
            String image = shopImageRepository.findTop1ByShop_Id(shop.getId()).map(ShopImage::getUrl).orElse("http://default");
            Favorite favorite = favoriteRepository.getUserLike(shop.getId(), user.getId());
            Boolean isFavorite = false;
            if (favorite !=null) {
                isFavorite=favorite.getIsFavorite();
            }
            return ShopResDto.ofShop(shop, image, isFavorite);
        }).toList();
        return shopResDtos;
    }

    public List<ShopResDto> getShopListBySpot(Long spotId) {

        User user = userService.getUser();

        List<ShopResDto> shopResDtos = shopRepository.findBySpotId(spotId).stream().map(shop -> {
            String image = shopImageRepository.findTop1ByShop_Id(shop.getId()).map(ShopImage::getUrl).orElse("http://default");
            Favorite favorite = favoriteRepository.getUserLike(shop.getId(), user.getId());
            Boolean isFavorite = false;
            if (favorite !=null) {
                isFavorite=favorite.getIsFavorite();
            }
            return ShopResDto.ofShop(shop, image, isFavorite);
        }).toList();
        return shopResDtos;
    }

    public List<ShopResDto> getShopListByMood(Long moodId) {

        User user = userService.getUser();

        List<ShopResDto> shopResDtos = shopRepository.findByMoodId(moodId).stream().map(shop -> {
            String image = shopImageRepository.findTop1ByShop_Id(shop.getId()).map(ShopImage::getUrl).orElse("http://default");
            Favorite favorite = favoriteRepository.getUserLike(shop.getId(), user.getId());
            Boolean isFavorite = false;
            if (favorite !=null) {
                isFavorite=favorite.getIsFavorite();
            }
            return ShopResDto.ofShop(shop, image, isFavorite);
        }).toList();
        return shopResDtos;
    }

    public List<ShopResDto> getShopListByMoodAndSpot(Long moodId, Long spotId) {

        User user = userService.getUser();

        List<ShopResDto> shopResDtos = shopRepository.findByMoodIdAndSpotId(moodId, spotId).stream().map(shop -> {
            String image = shopImageRepository.findTop1ByShop_Id(shop.getId()).map(ShopImage::getUrl).orElse("http://default");
            Favorite favorite = favoriteRepository.getUserLike(shop.getId(), user.getId());
            Boolean isFavorite = false;
            if (favorite !=null) {
                isFavorite=favorite.getIsFavorite();
            }
            return ShopResDto.ofShop(shop, image, isFavorite);
        }).toList();
        return shopResDtos;
    }
}
