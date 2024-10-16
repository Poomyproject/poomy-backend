package com.poomy.mainserver.mood.controller;

import com.poomy.mainserver.mood.api.MoodApi;
import com.poomy.mainserver.mood.dto.MoodNmPrefResDto;
import com.poomy.mainserver.mood.dto.MoodResDto;
import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.mapper.MoodMapper;
import com.poomy.mainserver.mood.service.MoodService;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.service.UserService;
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
public class MoodController implements MoodApi {

    private final MoodService moodService;
    private final UserService userService;
    private final MoodMapper moodMapper;

    @Override
    public ResponseEntity<ApiResult<List<MoodResDto>>> getMoods() {
        List<Mood> moods =  moodService.getMoods();
        List<MoodResDto> moodResDtos = moods.stream()
                .map(moodMapper::toMoodResDto).toList();
        return ResponseEntity.ok(ApiUtils.success(moodResDtos));
    }

    @Override
    public ResponseEntity<ApiResult<List<MoodNmPrefResDto>>> getMoodNamePreference() {
        User user = userService.getUser();
        List<MoodNmPrefResDto> moodNmPrefResDtos = moodService.getMoodNamePreference(user);
        return ResponseEntity.ok(ApiUtils.success(moodNmPrefResDtos));
    }
}
