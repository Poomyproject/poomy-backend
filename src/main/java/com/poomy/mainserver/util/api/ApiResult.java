package com.poomy.mainserver.util.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult<T> {

    private boolean success;
    private T response;

}
