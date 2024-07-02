package com.poomy.mainserver.demo;

import com.poomy.mainserver.demo.dto.DemoReqDto;
import com.poomy.mainserver.demo.dto.DemoResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController implements DemoApi {

    @Override
    public DemoResDto getDemoTest(DemoReqDto demoReqDto) {
        log.info("demoProperty : {}", demoReqDto.getDemoProperty());
        return DemoResDto.builder()
                .demoProperty(demoReqDto.getDemoProperty())
                .build();
    }
}