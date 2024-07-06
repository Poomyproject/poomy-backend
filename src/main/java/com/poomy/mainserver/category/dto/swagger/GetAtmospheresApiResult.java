package com.poomy.mainserver.category.dto.swagger;

import com.poomy.mainserver.category.dto.AtmosphereResDto;
import com.poomy.mainserver.util.api.ApiResult;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "getCategories 성공일 경우, response 되는 형식")
public class GetAtmospheresApiResult extends ApiResult<List<AtmosphereResDto>> {

    public GetAtmospheresApiResult(List<AtmosphereResDto> response) {
        super(response);
    }

}
