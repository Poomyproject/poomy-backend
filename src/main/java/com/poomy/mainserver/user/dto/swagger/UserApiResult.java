package com.poomy.mainserver.user.dto.swagger;

import com.poomy.mainserver.user.dto.res.UserResDto;
import com.poomy.mainserver.util.api.ApiResult;

public class UserApiResult extends ApiResult<UserResDto> {

    public UserApiResult(boolean success, UserResDto response) {
        super(success, response);
    }
}
