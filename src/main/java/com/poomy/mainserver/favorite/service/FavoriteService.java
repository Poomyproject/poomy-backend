package com.poomy.mainserver.favorite.service;

import com.poomy.mainserver.favorite.dto.FavoriteShopResDto;
import com.poomy.mainserver.favorite.dto.LikeShopResDto;
import com.poomy.mainserver.favorite.entity.Favorite;
import com.poomy.mainserver.favorite.repository.FavoriteRepository;
import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.home.entity.ShopImage;
import com.poomy.mainserver.home.repository.ShopImageRepository;
import com.poomy.mainserver.home.repository.ShopRepository;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ShopImageRepository shopImageRepository;
    private final UserService userService;
    private final ShopRepository shopRepository;

    public List<FavoriteShopResDto> getFavoriteShopList() {
        User user = userService.getUser();
        List<FavoriteShopResDto> favoriteShops = favoriteRepository.getAllFavoriteShop(user.getId()).stream().map(favorite -> {
            String image = shopImageRepository.findTop1ByShop_Id(favorite.getShop().getId()).map(ShopImage::getUrl).orElse("http://default");
            return FavoriteShopResDto.ofFavoriteShop(favorite, image);
        }).toList();
        return favoriteShops;
    }


    public LikeShopResDto updateLikeShopById(Long shopId) {
        User user = userService.getUser();
        Favorite checkfavorite = favoriteRepository.getFavoriteShop(user.getId(), shopId);
        if (checkfavorite == null) {
            favoriteRepository.insertUserToFavorite(user.getId(), shopId);
        } else if (!checkfavorite.getIsFavorite()) {
            favoriteRepository.updateLikeShopById(user.getId(), shopId);
        }
        Favorite favorite = favoriteRepository.getFavoriteShop(user.getId(), shopId);
        LikeShopResDto likeShopResDto = LikeShopResDto.of(favorite);
        return likeShopResDto;

    }

    public LikeShopResDto updateUnlikeShopById(Long shopId) {
        User user = userService.getUser();
        Favorite checkfavorite = favoriteRepository.getFavoriteShop(user.getId(), shopId);
        if (checkfavorite.getIsFavorite()) {
            favoriteRepository.updateUnlikeShopById(user.getId(), shopId);
        }
        Favorite favorite = favoriteRepository.getFavoriteShop(user.getId(), shopId);
        LikeShopResDto likeShopResDto = LikeShopResDto.of(favorite);
        return likeShopResDto;
    }


    public List<FavoriteShopResDto> getFavoriteShopListBySpotMood(Long moodId, Long spotId) {
        User user = userService.getUser();
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteRepository.getAllFavoriteShop(user.getId()).stream()
                .filter(favorite-> Objects.equals(favorite.getShop().getSpot().getId(), spotId) && Objects.equals(favorite.getShop().getMood().getId(), moodId))
                .map(favorite -> {
                    String image = shopImageRepository.findTop1ByShop_Id(favorite.getShop().getId()).map(ShopImage::getUrl).orElse("http://default");
                    return FavoriteShopResDto.ofFavoriteShop(favorite, image);
                }).toList();
        return favoriteShopResDtos;
    }

    public List<FavoriteShopResDto> getFavoriteShopListBySpot(Long spotId) {
        User user = userService.getUser();
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteRepository.getAllFavoriteShop(user.getId()).stream()
                .filter(favorite-> Objects.equals(favorite.getShop().getSpot().getId(), spotId)).map(favorite -> {
            String image = shopImageRepository.findTop1ByShop_Id(favorite.getShop().getId()).map(ShopImage::getUrl).orElse("http://default");
            return FavoriteShopResDto.ofFavoriteShop(favorite, image);
        }).toList();
        return favoriteShopResDtos;
    }

    public List<FavoriteShopResDto> getFavoriteShopListByMood(Long moodId) {
        User user = userService.getUser();
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteRepository.getAllFavoriteShop(user.getId()).stream()
                .filter(favorite-> Objects.equals(favorite.getShop().getMood().getId(), moodId)).map(favorite -> {
                    String image = shopImageRepository.findTop1ByShop_Id(favorite.getShop().getId()).map(ShopImage::getUrl).orElse("http://default");
                    return FavoriteShopResDto.ofFavoriteShop(favorite, image);
                }).toList();
        return favoriteShopResDtos;
    }
}


