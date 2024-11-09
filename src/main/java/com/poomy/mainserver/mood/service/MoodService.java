package com.poomy.mainserver.mood.service;

import com.poomy.mainserver.mood.dto.MoodNmPrefResDto;
import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.mapper.MoodMapper;
import com.poomy.mainserver.mood.repository.MoodRepository;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MoodService {

    private final MoodRepository moodRepository;
    private final MoodMapper moodMapper;

    public List<Mood> getMoods(){
        return moodRepository.findAll();
    }

    public List<Mood> getMoods(List<Long> moodIds){
        return moodIds.stream()
                .map(moodId -> moodRepository.findById(moodId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, moodId + " of moodId")))
                .toList();
    }

    public List<MoodNmPrefResDto> getMoodNamePreference(User user) {
        List<Long> preferredMoodIds = user.getUserMoods().stream().map(userMood -> userMood.getMood().getId()).toList();
        List<Mood> moods = getMoods();
        return moods.stream().map(mood -> {
            MoodNmPrefResDto moodNmPrefResDto = moodMapper.toMoodNmPrefResDto(mood);
            moodNmPrefResDto.setIsPreferred(preferredMoodIds.contains(moodNmPrefResDto.getId()));
            return moodNmPrefResDto;
        }).toList();
    }

}
