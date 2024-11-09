package com.poomy.mainserver.detail.controller;


import com.poomy.mainserver.detail.dto.res.ShopDetailRes;
import com.poomy.mainserver.detail.service.ShopDetailService;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopDetailController {

    private final ShopDetailService shopDetailService;
    private final UserService userService;

    @GetMapping("/{shopId}")
    public ResponseEntity<ApiResult<ShopDetailRes>> getShopDetail(@PathVariable Long shopId) {
        return ResponseEntity.ok(ApiUtils.success(shopDetailService.getShopDetail(shopId, userService.getUser())));
    }
}
