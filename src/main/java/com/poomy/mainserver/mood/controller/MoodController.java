package com.poomy.mainserver.mood.controller;

import com.poomy.mainserver.mood.api.MoodApi;
import com.poomy.mainserver.mood.dto.MoodResDto;
import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.mapper.MoodMapper;
import com.poomy.mainserver.mood.service.MoodService;
import com.poomy.mainserver.util.api.ApiResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class MoodController implements MoodApi {

    private final MoodService moodService;
    private final MoodMapper moodMapper;

    @Override
    public ResponseEntity<ApiResult<List<MoodResDto>>> getMoods() {
        List<Mood> moods =  moodService.getMoods();
        List<MoodResDto> moodResDtos = moods.stream()
                .map(moodMapper::toMoodResDto).toList();
        return ResponseEntity.ok(new ApiResult<>(moodResDtos));
    }
}
