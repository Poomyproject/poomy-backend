package com.poomy.mainserver.mood.service;

import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.repository.MoodRepository;
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

    public List<Mood> getMoods(){
        return moodRepository.findAll();
    }

    public List<Mood> getMoods(List<Integer> moodIds){
        return moodIds.stream()
                .map(moodId -> moodRepository.findById(moodId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, moodId + " of moodId")))
                .toList();
    }

}
