package com.poomy.mainserver.home.controller;

import com.poomy.mainserver.home.dto.res.*;
import com.poomy.mainserver.home.service.ShopService;
import com.poomy.mainserver.newsletter.service.NewsletterService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final ShopService shopService;
    private final NewsletterService newsletterService;
    private final UserService userService;

    @GetMapping("/spot/shop")
    public ResponseEntity<ApiResult<HomeShopRes>> getShopsBySpot() {
        return ResponseEntity.ok(new ApiResult<>(shopService.getShopListBySpot(userService.getUser())));
    }

    @GetMapping("/spot")
    public ResponseEntity<ApiResult<List<SpotsRes>>> getSpots() {
        return ResponseEntity.ok(new ApiResult<>(shopService.getSpotsList()));
    }

    @GetMapping("/newsletter")
    public ResponseEntity<ApiResult<List<HomeNewsletterRes>>> getNewsLetters() {
        return ResponseEntity.ok(new ApiResult<>(newsletterService.getRandomNewsletters()));
    }

    @GetMapping("/mood/shop")
    public ResponseEntity<ApiResult<List<HomeShopRes>>> getShopsByMood() {
        return ResponseEntity.ok(new ApiResult<>(shopService.getShopListByMood(userService.getUser())));
    }


}
