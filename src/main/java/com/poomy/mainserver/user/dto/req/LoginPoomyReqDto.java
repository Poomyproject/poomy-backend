package com.poomy.mainserver.user.dto.req;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginPoomyReqDto {

    @Email(message = "google email을 넘겨주세요")
    private String googleEmail;

}
