package com.poomy.mainserver.category.api;

import com.poomy.mainserver.category.dto.CategoryResDto;
import com.poomy.mainserver.category.dto.swagger.CategoryApiResult;
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

@Tag(name = "Atmosphere API", description = "사용자가 선택한 취향과 관련된 API")
@RequestMapping("/api/categories")
public interface CategoryApi {

    @Operation(summary = "선택 가능한 취향 리스트", description = "사용자가 선택 가능한 취향들을 불러온다.")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CategoryApiResult.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ApiErrorResult.class)))
    @GetMapping("/atmospheres")
    ResponseEntity<ApiResult<List<CategoryResDto>>> getAtmospheres();

    @Operation(summary = "선택 가능한 핫 플레이스 리스트", description = "사용자가 선택 가능한 핫 플레이스들을 불러온다.")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CategoryApiResult.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ApiErrorResult.class)))
    @GetMapping("/hot-places")
    ResponseEntity<ApiResult<List<CategoryResDto>>> getHotPlaces();

}
