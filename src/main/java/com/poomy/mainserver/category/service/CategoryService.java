package com.poomy.mainserver.category.service;

import com.poomy.mainserver.category.entity.AtmosphereEntity;
import com.poomy.mainserver.category.entity.HotPlaceEntity;
import com.poomy.mainserver.category.repository.AtmosphereRepository;
import com.poomy.mainserver.category.repository.HotPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final AtmosphereRepository atmosphereRepository;
    private final HotPlaceRepository hotPlaceRepository;

    @Transactional
    public List<AtmosphereEntity> getAtmospheres() {
        return atmosphereRepository.findAll();
    }

    @Transactional
    public List<HotPlaceEntity> getHotPlaces(){
        return hotPlaceRepository.findAll();
    }
}
