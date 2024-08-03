package com.poomy.mainserver.user.dto.swagger;

import com.poomy.mainserver.user.dto.UserSpotResDto;
import com.poomy.mainserver.util.api.ApiResult;

import java.util.List;

public class UserSpotApiResult extends ApiResult<List<UserSpotResDto>> {
    public UserSpotApiResult(List<UserSpotResDto> response) {
        super(response);
    }
}
