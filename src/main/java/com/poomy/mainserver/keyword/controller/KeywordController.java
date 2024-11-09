package com.poomy.mainserver.keyword.controller;

import com.poomy.mainserver.favorite.dto.FavoriteShopResDto;
import com.poomy.mainserver.favorite.service.FavoriteService;
import com.poomy.mainserver.keyword.dto.ShopResDto;
import com.poomy.mainserver.keyword.service.KeywordService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/keyword")

public class KeywordController {


    private final KeywordService keywordService;

    @GetMapping("")
    public ResponseEntity<ApiResult<List<ShopResDto>>> getShops() {
        List<ShopResDto> shopResDtos = keywordService.getShopList();
        return ResponseEntity.ok(ApiUtils.success(shopResDtos));
    }

    @GetMapping("/mood/{moodId}")
    public ResponseEntity<ApiResult<List<ShopResDto>>> getShopsByMood(@PathVariable Long moodId) {
        List<ShopResDto> shopResDtos = keywordService.getShopListByMood(moodId);
        return ResponseEntity.ok(ApiUtils.success(shopResDtos));
    }

    @GetMapping("/spot/{spotId}")
    public ResponseEntity<ApiResult<List<ShopResDto>>> getShopsBySpot(@PathVariable Long spotId) {
        List<ShopResDto> shopResDtos = keywordService.getShopListBySpot(spotId);
        return ResponseEntity.ok(ApiUtils.success(shopResDtos));
    }

    @GetMapping("/mood/{moodId}/spot/{spotId}")
    public ResponseEntity<ApiResult<List<ShopResDto>>> getShopsByMoodAndSpot(@PathVariable Long moodId, @PathVariable Long spotId) {
        List<ShopResDto> shopResDtos = keywordService.getShopListByMoodAndSpot(moodId, spotId);
        return ResponseEntity.ok(ApiUtils.success(shopResDtos));
    }
}
