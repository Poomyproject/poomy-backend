package com.poomy.mainserver.mood.dto.swagger;

import com.poomy.mainserver.mood.dto.MoodResDto;
import com.poomy.mainserver.util.api.ApiResult;

import java.util.List;

public class MoodApiResult extends ApiResult<List<MoodResDto>> {
    public MoodApiResult(boolean success, List<MoodResDto> response) {
        super(success, response);
    }
}
