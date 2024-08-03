package com.poomy.mainserver.spot.api;

import com.poomy.mainserver.spot.dto.SpotResDto;
import com.poomy.mainserver.spot.dto.swagger.SpotApiResult;
import com.poomy.mainserver.util.api.ApiErrorResult;
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
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ApiErrorResult.class)))
    @GetMapping("")
    ResponseEntity<ApiResult<List<SpotResDto>>> getSpots();

}
