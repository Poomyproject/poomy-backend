package com.poomy.mainserver.util.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(name = "성공일 경우 Response")
public class ApiResult<T> {

    @Schema(description = "API 호출 성공 여부", example = "true")
    private final boolean success = true;
    private final T response;

    public ApiResult(T response){
        this.response = response;
    }

}
