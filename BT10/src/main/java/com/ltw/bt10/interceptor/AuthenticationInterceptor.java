package com.ltw.bt10.interceptor;

import com.ltw.bt10.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String requestURI = request.getRequestURI();

        if (requestURI.equals("/login") ||
                requestURI.equals("/register") ||
                requestURI.startsWith("/css/") ||
                requestURI.startsWith("/js/") ||
                requestURI.startsWith("/images/")) {
            return true;
        }

        if (requestURI.startsWith("/admin")) {
            if (loggedInUser == null || !"ADMIN".equals(loggedInUser.getRole().name())) {
                response.sendRedirect("/login?error=unauthorized");
                return false;
            }
        }

        if (requestURI.startsWith("/user") && !requestURI.equals("/login")) {
            if (loggedInUser == null || !"USER".equals(loggedInUser.getRole().name())) {
                response.sendRedirect("/login?error=unauthorized");
                return false;
            }
        }

        if (requestURI.equals("/") || requestURI.equals("/login")) {
            if (loggedInUser != null) {
                if ("ADMIN".equals(loggedInUser.getRole().name())) {
                    response.sendRedirect("/admin/dashboard");
                    return false;
                } else if ("USER".equals(loggedInUser.getRole().name())) {
                    response.sendRedirect("/user/dashboard");
                    return false;
                }
            }
        }

        return true;
    }
}
