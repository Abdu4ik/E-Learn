package com.tafakkoor.englishlearningplatform.filters.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.LogRecord;

@Log
@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    private static final List<String> ALLOWED_PATHS = List.of(
            "/login",
            "/logout",
            "/"
    );
    private static final Predicate<String> IS_ALLOWED_PATH = path -> ALLOWED_PATHS.stream().anyMatch(path::startsWith);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (IS_ALLOWED_PATH.test(path)) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                res.sendRedirect(req.getContextPath() + "/login");
            } else {
                chain.doFilter(request, response);
            }
        }
    }
}
