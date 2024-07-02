package com.poomy.mainserver.demo;

import com.poomy.mainserver.demo.dto.DemoReqDto;
import com.poomy.mainserver.demo.dto.DemoResDto;
import com.poomy.mainserver.util.api.ApiErrorResult;
import com.poomy.mainserver.util.api.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Demo API", description = "demoProperty가 0이면 에러를 반환하고 0이 아니면 성공을 반환한다.")
@RequestMapping("/api/demo")
public interface DemoApi {

    @Operation(summary = "테스트", description = "Swagger 테스트를 위한 API")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ApiErrorResult.class)))
    @PostMapping
    ResponseEntity<ApiResult<DemoResDto>> getDemoTest(@RequestBody DemoReqDto demoReqDto);

}