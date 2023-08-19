package com.after.sweating.security.filter

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val exception : String = request?.getAttribute("exception") as String
        val errorCode : ErrorCode

    }
}


@Override
public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    String exception = (String)request.getAttribute("exception");
    ErrorCode errorCode;

    log.debug("log: exception: {} ", exception);

    /**
     * 토큰 없는 경우
     */
    if(exception == null) {
        errorCode = ErrorCode.NON_LOGIN;
        setResponse(response, errorCode);
        return;
    }

    /**
     * 토큰 만료된 경우
     */
    if(exception.equals(ErrorCode.EXPIRED_TOKEN.getCode())) {
        errorCode = ErrorCode.EXPIRED_TOKEN;
        setResponse(response, errorCode);
        return;
    }

    /**
     * 토큰 시그니처가 다른 경우
     */
    if(exception.equals(ErrorCode.INVALID_TOKEN.getCode())) {
        errorCode = ErrorCode.INVALID_TOKEN;
        setResponse(response, errorCode);
    }
}

/**
 * 한글 출력을 위해 getWriter() 사용
 */
private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().println("{ \"message\" : \"" + errorCode.getMessage()
            + "\", \"code\" : \"" +  errorCode.getCode()
            + "\", \"status\" : " + errorCode.getStatus()
            + ", \"errors\" : [ ] }");
}

}