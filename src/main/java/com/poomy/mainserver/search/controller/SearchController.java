package com.poomy.mainserver.search.controller;

import com.poomy.mainserver.search.dto.SearchCountResDto;
import com.poomy.mainserver.search.dto.SearchShopResDto;
import com.poomy.mainserver.search.dto.TopFiveShopResDto;
import com.poomy.mainserver.search.service.SearchService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiResult<List<SearchShopResDto>>> getShopsByName(@RequestParam String word) {
        List<SearchShopResDto> searchShopResDtos = searchService.getShopListByName(word);
        return ResponseEntity.ok(ApiUtils.success(searchShopResDtos));
    }

    @GetMapping("/topFive")
    public ResponseEntity<ApiResult<List<TopFiveShopResDto>>> getTopFiveShop() {
        List<TopFiveShopResDto> topFiveShopRes = searchService.getTopFiveShop();
        return ResponseEntity.ok(ApiUtils.success(topFiveShopRes));
    }

    @PostMapping("/{shopId}")
    public ResponseEntity<ApiResult<SearchCountResDto>> increaseSearchCount(@PathVariable Long shopId) {
        SearchCountResDto searchCountResDto = searchService.updateSearchCountById(shopId);
        return ResponseEntity.ok(ApiUtils.success(searchCountResDto));
    }
}
