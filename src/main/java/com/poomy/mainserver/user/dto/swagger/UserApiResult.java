package com.poomy.mainserver.user.dto.swagger;

import com.poomy.mainserver.user.dto.UserResDto;
import com.poomy.mainserver.util.api.ApiResult;

public class UserApiResult extends ApiResult<UserResDto> {
    public UserApiResult(UserResDto response) {
        super(response);
    }
}
