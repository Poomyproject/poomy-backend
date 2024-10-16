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

import java.util.List;

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
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteRepository.getAllFavoriteShop(user.getId()).stream().map(favorite -> {
            Shop filteredShop = shopRepository.getFilteredShops(moodId, spotId, favorite.getShop().getId());
            Favorite favoriteShop = favoriteRepository.getFavoriteShop(user.getId(), filteredShop.getId());
            String image = shopImageRepository.findTop1ByShop_Id(favoriteShop.getShop().getId()).map(ShopImage::getUrl).orElse("http://default");
            return FavoriteShopResDto.ofFavoriteShop(favoriteShop, image);
        }).toList();
        return favoriteShopResDtos;
    }

    public List<FavoriteShopResDto> getFavoriteShopListBySpot(Long spotId) {
        User user = userService.getUser();
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteRepository.getAllFavoriteShop(user.getId()).stream().map(favorite -> {
            Shop filteredShop = shopRepository.getFilteredShopsBySpot(spotId, favorite.getShop().getId());
            Favorite favoriteShop = favoriteRepository.getFavoriteShop(user.getId(), filteredShop.getId());
            String image = shopImageRepository.findTop1ByShop_Id(favoriteShop.getShop().getId()).map(ShopImage::getUrl).orElse("http://default");
            return FavoriteShopResDto.ofFavoriteShop(favoriteShop, image);
        }).toList();
        return favoriteShopResDtos;
    }

    public List<FavoriteShopResDto> getFavoriteShopListByMood(Long moodId) {
        User user = userService.getUser();
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteRepository.getAllFavoriteShop(user.getId()).stream().map(favorite -> {
            Shop filteredShop = shopRepository.getFilteredShopsByMood(moodId, favorite.getShop().getId());
            Favorite favoriteShop = favoriteRepository.getFavoriteShop(user.getId(), filteredShop.getId());
            String image = shopImageRepository.findTop1ByShop_Id(favoriteShop.getShop().getId()).map(ShopImage::getUrl).orElse("http://default");
            return FavoriteShopResDto.ofFavoriteShop(favoriteShop, image);
        }).toList();
        return favoriteShopResDtos;
    }
}


