package com.poomy.mainserver.category.service;

import com.poomy.mainserver.category.entity.Atmosphere;
import com.poomy.mainserver.category.entity.HotPlace;
import com.poomy.mainserver.category.repository.AtmosphereRepository;
import com.poomy.mainserver.category.repository.HotPlaceRepository;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {

    private final AtmosphereRepository atmosphereRepository;
    private final HotPlaceRepository hotPlaceRepository;

    public List<Atmosphere> getAtmospheres() {
        return atmosphereRepository.findAll();
    }

    public List<HotPlace> getHotPlaces(){
        return hotPlaceRepository.findAll();
    }

    public List<Atmosphere> getAtmospheres(List<Integer> atmosphereIds){
        return atmosphereIds.stream()
                .map(atmosphereId -> atmosphereRepository.findById(atmosphereId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, atmosphereId + " of atmosphereId")))
                .toList();
    }

    public List<HotPlace> getHotPlaces(List<Integer> hotPlaceIds){
        return hotPlaceIds.stream()
                .map(hotPlaceId -> hotPlaceRepository.findById(hotPlaceId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, hotPlaceId + " of hotPlaceId")))
                .toList();
    }
}
