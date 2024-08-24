package com.poomy.mainserver.spot.controller;

import com.poomy.mainserver.spot.api.SpotApi;
import com.poomy.mainserver.spot.dto.SpotResDto;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.spot.mapper.SpotMapper;
import com.poomy.mainserver.spot.service.SpotService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class SpotController implements SpotApi {

    private final SpotService spotService;
    private final SpotMapper spotMapper;

    @Override
    public ResponseEntity<ApiResult<List<SpotResDto>>> getSpots() {
        List<Spot> spots = spotService.getSpots();
        List<SpotResDto> spotResDtos = spots.stream()
                .map(spotMapper::toSpotResDto).toList();
        return ResponseEntity.ok(ApiUtils.success(spotResDtos));
    }
}
