package com.poomy.mainserver.spot.service;

import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.spot.repository.SpotRepository;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    public List<Spot> getSpots(){
        return spotRepository.findAll();
    }

    public List<Spot> getSpots(List<Integer> spotIds){
        return spotIds.stream()
                .map(spotId -> spotRepository.findById(spotId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, spotId + " of spotId")))
                .toList();
    }
}
