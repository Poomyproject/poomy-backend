package com.poomy.mainserver.home.controller;

import com.poomy.mainserver.home.dto.res.HomeNewsletterRes;
import com.poomy.mainserver.home.dto.res.HomeShopRes;
import com.poomy.mainserver.home.dto.res.SpotsRes;
import com.poomy.mainserver.home.service.ShopService;
import com.poomy.mainserver.newsletter.dto.NewsLetterResDto;
import com.poomy.mainserver.newsletter.entity.Newsletter;
import com.poomy.mainserver.newsletter.service.NewsletterService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "home api", description = "메인 홈 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final ShopService shopService;
    private final NewsletterService newsletterService;
    private final UserService userService;

    @GetMapping("/spot/shop")
    @Operation(summary = "장소별 소품샵 리스트 조회", description = "사용자가 선택한 장소에 따른 소품샵 6개 랜덤 조회 api")
    public ResponseEntity<ApiResult<HomeShopRes>> getShopsBySpot() {
        return ResponseEntity.ok(ApiUtils.success(shopService.getShopListBySpot(userService.getUser())));
    }

    @GetMapping("/spot")
    @Operation(summary = "지역 이름 리스트 조회", description = "지역 이름 랜덤 6개 조회하는 api")
    public ResponseEntity<ApiResult<List<SpotsRes>>> getSpots() {
        return ResponseEntity.ok(ApiUtils.success(shopService.getSpotsList()));
    }

    @GetMapping("/newsletter")
    @Operation(summary = "뉴스레터 조회", description = "뉴스레터 랜덤 3개 조회하는 api")
    public ResponseEntity<ApiResult<List<NewsLetterResDto>>> getNewsLetters() {
        return ResponseEntity.ok(ApiUtils.success(newsletterService.getRandomNewsletters()));
    }

    @GetMapping("/mood/shop")
    @Operation(summary = "분위기별 소품샵 리스트 조회", description = "사용자가 선택한 분위기에 따른 소품샵 6개 랜덤 조회 api")
    public ResponseEntity<ApiResult<List<HomeShopRes>>> getShopsByMood() {
        return ResponseEntity.ok(ApiUtils.success(shopService.getShopListByMood(userService.getUser())));
    }

}
