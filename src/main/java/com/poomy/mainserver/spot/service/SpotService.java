package com.poomy.mainserver.spot.service;

import com.poomy.mainserver.spot.dto.SpotNmPrefResDto;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.spot.mapper.SpotMapper;
import com.poomy.mainserver.spot.repository.SpotRepository;
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
public class SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;

    public List<Spot> getSpots(){
        return spotRepository.findAll();
    }

    public List<Spot> getSpots(List<Integer> spotIds){
        return spotIds.stream()
                .map(spotId -> spotRepository.findById(spotId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, spotId + " of spotId")))
                .toList();
    }

    public List<SpotNmPrefResDto> getSpotNamePreference(User user) {
        List<Long> preferredSpotIds = user.getUserSpots().stream().map(userSpot -> userSpot.getSpot().getId()).toList();
        List<Spot> spots = getSpots();
        return spots.stream().map(spot -> {
            SpotNmPrefResDto spotNmPrefResDto = spotMapper.toSpotNmPrefResDto(spot);
            spotNmPrefResDto.setIsPreferred(preferredSpotIds.contains(spotNmPrefResDto.getId()));
            return spotNmPrefResDto;
        }).toList();
    }
}
