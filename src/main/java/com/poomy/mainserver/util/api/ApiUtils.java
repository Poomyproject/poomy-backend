package com.poomy.mainserver.util.api;

public class ApiUtils {

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, data);
    }
    public static <T> ApiResult<T> error(T data) {
        return new ApiResult<>(false, data);
    }

}
