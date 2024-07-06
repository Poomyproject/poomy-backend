package com.poomy.mainserver.category.controller;

import com.poomy.mainserver.category.api.CategoryApi;
import com.poomy.mainserver.category.dto.AtmosphereResDto;
import com.poomy.mainserver.category.entity.AtmosphereEntity;
import com.poomy.mainserver.category.mapper.CategoryMapper;
import com.poomy.mainserver.category.service.CategoryService;
import com.poomy.mainserver.util.api.ApiResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseEntity<ApiResult<List<AtmosphereResDto>>> getAtmospheres() {
        List<AtmosphereEntity> atmospheres = categoryService.getAtmospheres();
        List<AtmosphereResDto> atmosphereResDtos = atmospheres.stream()
                .map(categoryMapper::toAtmosphereResDto).toList();
        return ResponseEntity.ok(new ApiResult<>(atmosphereResDtos));
    }
}
