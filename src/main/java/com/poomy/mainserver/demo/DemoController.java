package com.poomy.mainserver.demo;

import com.poomy.mainserver.demo.dto.DemoReqDto;
import com.poomy.mainserver.demo.dto.DemoResDto;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController implements DemoApi{

    @Override
    public ResponseEntity<ApiResult<DemoResDto>> getDemoTest(DemoReqDto demoReqDto){

        if(demoReqDto.getDemoProperty() == 0){
            throw new CommonException(BError.FAIL, "demo");
        }

        DemoResDto demoResDto =  DemoResDto.builder()
                .demoProperty(demoReqDto.getDemoProperty())
                .build();

        return ResponseEntity.ok(new ApiResult<>(demoResDto));
    }


}
