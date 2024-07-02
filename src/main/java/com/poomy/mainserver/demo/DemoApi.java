package com.poomy.mainserver.demo;

import com.poomy.mainserver.demo.dto.DemoReqDto;
import com.poomy.mainserver.demo.dto.DemoResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Demo API", description = "테스트 API")
@RequestMapping("/api/demo")
public interface DemoApi {

    @Operation(summary = "테스트", description = "Swagger 테스트를 위한 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    DemoResDto getDemoTest(@RequestBody DemoReqDto demoReqDto);

}