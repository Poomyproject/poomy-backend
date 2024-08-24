package com.poomy.mainserver.user.dto.req;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterUserMoodsReqDto {

    @Size(min = 1, max = 2, message = "사용자 취향은 1개 이상 2개 이하여야 합니다.")
    List<Integer> moodIds;

}
