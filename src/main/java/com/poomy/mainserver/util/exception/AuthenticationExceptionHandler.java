package com.poomy.mainserver.util.exception;

import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import com.poomy.mainserver.util.exception.ErrorResponse.FieldError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException {

        String errorCode = (String) request.getAttribute("exception");
        if (errorCode == null) {
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        }

        if (errorCode.equals(ErrorCode.UNAUTHORIZED.getCode())) {
            setErrorResponse(request, response, ErrorCode.UNAUTHORIZED);
        } else if (errorCode.equals(ErrorCode.INVALID_TOKEN.getCode())) {
            setErrorResponse(request, response, ErrorCode.INVALID_TOKEN);
        } else {
            setErrorResponse(request, response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void setErrorResponse(HttpServletRequest request, HttpServletResponse response, ErrorCode errorCode) throws IOException {
        List<FieldError> fieldErrors = FieldError.of("token exception",
                (String) request.getAttribute("error"),
                (String) request.getAttribute("errorMessage"));
        ApiResult result = ApiUtils.error(ErrorResponse.of(errorCode, fieldErrors));
        response.setStatus(errorCode.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
