package com.poomy.mainserver.mood.api;

import com.poomy.mainserver.mood.dto.MoodNmPrefResDto;
import com.poomy.mainserver.mood.dto.MoodResDto;
import com.poomy.mainserver.util.api.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Mood API", description = "분위기 관련 API")
@RequestMapping("/api/moods")
public interface MoodApi {

    @Operation(summary = "선택 가능한 분위기 리스트", description = "사용자가 선택 가능한 분위기들을 불러온다.")
    @GetMapping("")
    ResponseEntity<ApiResult<List<MoodResDto>>> getMoods();

    @Operation(summary = "사용자 취향을 고려한 분위기 리스트", description = "사용자가 선호 여부를 고려하여 전체 mood 리스트 조회")
    @GetMapping("/with-preference")
    ResponseEntity<ApiResult<List<MoodNmPrefResDto>>> getMoodNamePreference();

}
