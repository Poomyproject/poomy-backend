package com.poomy.mainserver.spot.api;

import com.poomy.mainserver.spot.dto.SpotNmPrefResDto;
import com.poomy.mainserver.spot.dto.SpotResDto;
import com.poomy.mainserver.spot.dto.swagger.SpotApiResult;
import com.poomy.mainserver.util.api.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Spot API", description = "핫플레이스 관련 API")
@RequestMapping("/api/spots")
public interface SpotApi {

    @Operation(summary = "선택 가능한 핫 플레이스 리스트", description = "사용자가 선택 가능한 핫 플레이스들을 불러온다.")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = SpotApiResult.class)))
    @GetMapping("")
    ResponseEntity<ApiResult<List<SpotResDto>>> getSpots();

    @Operation(summary = "사용자 취향을 고려한 핫플레이스 리스트", description = "사용자가 선호 여부를 고려하여 전체 spot 리스트 조회")
    @GetMapping("/with-preference")
    ResponseEntity<ApiResult<List<SpotNmPrefResDto>>> getSpotNamePreference();


}
