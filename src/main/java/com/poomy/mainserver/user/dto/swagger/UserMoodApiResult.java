package com.poomy.mainserver.user.dto.swagger;

import com.poomy.mainserver.user.dto.UserMoodResDto;
import com.poomy.mainserver.util.api.ApiResult;

import java.util.List;

public class UserMoodApiResult extends ApiResult<List<UserMoodResDto>> {

    public UserMoodApiResult(boolean success, List<UserMoodResDto> response) {
        super(success, response);
    }
}
