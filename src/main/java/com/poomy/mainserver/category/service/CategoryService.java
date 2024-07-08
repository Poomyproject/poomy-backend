package com.poomy.mainserver.category.service;

import com.poomy.mainserver.category.entity.Atmosphere;
import com.poomy.mainserver.category.entity.HotPlace;
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
    public List<Atmosphere> getAtmospheres() {
        return atmosphereRepository.findAll();
    }

    @Transactional
    public List<HotPlace> getHotPlaces(){
        return hotPlaceRepository.findAll();
    }
}
