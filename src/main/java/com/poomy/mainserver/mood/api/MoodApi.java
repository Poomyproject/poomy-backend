package com.poomy.mainserver.mood.api;

import com.poomy.mainserver.mood.dto.MoodResDto;
import com.poomy.mainserver.mood.dto.swagger.MoodApiResult;
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

@Tag(name = "Mood API", description = "분위기 관련 API")
@RequestMapping("/api/moods")
public interface MoodApi {

    @Operation(summary = "선택 가능한 분위기 리스트", description = "사용자가 선택 가능한 분위기들을 불러온다.")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = MoodApiResult.class)))
    @GetMapping("")
    ResponseEntity<ApiResult<List<MoodResDto>>> getMoods();

}
