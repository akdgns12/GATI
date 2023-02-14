package com.family.gati.security.jwt;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
// 사용자가 권한없는 요청시 403 Forbidden 처리(인가)
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        System.out.println("JwtAccessDeniedHandler");
        handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
    }
}
