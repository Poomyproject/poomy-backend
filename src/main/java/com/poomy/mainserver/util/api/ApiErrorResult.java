package com.poomy.mainserver.util.api;

import com.poomy.mainserver.util.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(name = "실패일 경우 Response")
public class ApiErrorResult {

    @Schema(description = "API 호출 성공 여부", example = "false")
    private final boolean success = false;
    ErrorResponse response;

    public ApiErrorResult(ErrorResponse response){
        this.response = response;
    }

}
