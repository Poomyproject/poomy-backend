package com.poomy.mainserver.category.dto.swagger;

import com.poomy.mainserver.category.dto.CategoryResDto;
import com.poomy.mainserver.util.api.ApiResult;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "CategoryAPI 성공일 경우, 기본적인 id, name, imgUrl response하는 방식")
public class CategoryApiResult extends ApiResult<List<CategoryResDto>> {

    public CategoryApiResult(List<CategoryResDto> response) {
        super(response);
    }

}
