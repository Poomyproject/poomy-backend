package com.poomy.mainserver.category.service;

import com.poomy.mainserver.category.entity.AtmosphereEntity;
import com.poomy.mainserver.category.repository.AtmosphereRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final AtmosphereRepository atmosphereRepository;

    @Transactional
    public List<AtmosphereEntity> getAtmospheres() {
        return atmosphereRepository.findAll();
    }
}
