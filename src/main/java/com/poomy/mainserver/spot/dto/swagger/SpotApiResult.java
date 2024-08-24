package com.poomy.mainserver.spot.dto.swagger;

import com.poomy.mainserver.spot.dto.SpotResDto;
import com.poomy.mainserver.util.api.ApiResult;

import java.util.List;

public class SpotApiResult extends ApiResult<List<SpotResDto>> {
    public SpotApiResult(boolean success, List<SpotResDto> response) {
        super(success, response);
    }
}
