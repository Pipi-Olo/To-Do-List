package com.pipiolo.itemshop.web.login;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception
    ) throws IOException, ServletException {
        if (exception instanceof UsernameNotFoundException) {
            request.setAttribute("errorMessage", "로그인에 실패했습니다. 다시 시도해 줏세요.");
        }

        response.sendRedirect("/login");
    }
}
