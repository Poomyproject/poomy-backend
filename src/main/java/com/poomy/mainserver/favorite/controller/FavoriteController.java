package com.poomy.mainserver.favorite.controller;

import com.poomy.mainserver.favorite.dto.FavoriteShopResDto;
import com.poomy.mainserver.favorite.dto.LikeShopResDto;
import com.poomy.mainserver.favorite.service.FavoriteService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/favorite")

public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("")
    public ResponseEntity<ApiResult<List<FavoriteShopResDto>>> getFavoriteShops() {
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteService.getFavoriteShopList();
        return ResponseEntity.ok(ApiUtils.success(favoriteShopResDtos));
    }

    @GetMapping("/mood/{moodId}/spot/{spotId}")
    public ResponseEntity<ApiResult<List<FavoriteShopResDto>>> getFavoriteShopsBySpotMood(@PathVariable Long moodId, @PathVariable Long spotId) {
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteService.getFavoriteShopListBySpotMood(moodId, spotId);
        return ResponseEntity.ok(ApiUtils.success(favoriteShopResDtos));
    }

    @GetMapping("/mood/{moodId}")
    public ResponseEntity<ApiResult<List<FavoriteShopResDto>>> getFavoriteShopsByMood(@PathVariable Long moodId) {
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteService.getFavoriteShopListByMood(moodId);
        return ResponseEntity.ok(ApiUtils.success(favoriteShopResDtos));
    }

    @GetMapping("/spot/{spotId}")
    public ResponseEntity<ApiResult<List<FavoriteShopResDto>>> getFavoriteShopsBySpot(@PathVariable Long spotId) {
        List<FavoriteShopResDto> favoriteShopResDtos = favoriteService.getFavoriteShopListBySpot(spotId);
        return ResponseEntity.ok(ApiUtils.success(favoriteShopResDtos));
    }

    @PostMapping("/{shopId}/like")
    public ResponseEntity<ApiResult<LikeShopResDto>> insertLikeShop(@PathVariable Long shopId) {
        LikeShopResDto likeShopResDto = favoriteService.updateLikeShopById(shopId);
        return ResponseEntity.ok(ApiUtils.success(likeShopResDto));
    }

    @PostMapping("/{shopId}/unlike")
    public ResponseEntity<ApiResult<LikeShopResDto>> deleteLikeShop(@PathVariable Long shopId) {
        LikeShopResDto likeShopResDto = favoriteService.updateUnlikeShopById(shopId);
        return ResponseEntity.ok(ApiUtils.success(likeShopResDto));
    }


}

