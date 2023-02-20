package com.tafakkoor.englishlearningplatform.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter(filterName = "LoginValidationFilter", urlPatterns = {"/login"})
public class LoginValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String loginUsername = req.getParameter("in_username");
        String loginPassword = req.getParameter("in_password");

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        if (isFilled(username, email, password, confirmPassword) && isFilled(loginUsername, loginPassword)) {
            req.setAttribute("too_many_fields_error", "You can't fill the fields both in login and register!");
            req.getRequestDispatcher("views/authorization/login.jsp").forward(req, res);
        } else if (isFilled(username, email, password, confirmPassword) && !isFilled(loginUsername, loginPassword)) {
            if (!password.equals(confirmPassword)) {
                req.setAttribute("pass_conf_err", "Passwords don't match!");
                req.setAttribute("page", "up");
                req.getRequestDispatcher("views/authorization/login.jsp").forward(req, res);
            } else {
                req.setAttribute("message", "register");
                chain.doFilter(request, response);
            }
        } else if (isFilled(loginUsername, loginPassword) && !isFilled(username, email, password, confirmPassword)) {
            req.setAttribute("message", "login");
            chain.doFilter(request, response);
        } else {
            req.setAttribute("fields_not_filled_err", "Please fill the fields!");
            req.getRequestDispatcher("views/authorization/login.jsp").forward(req, res);
        }

    }
    // check if all fields are filled
    public static boolean isFilled(String username, String email, String password, String confirmPassword) {
        if (username == null || username.isEmpty()) {
            return false;
        } else if (email == null || email.isEmpty()) {
            return false;
        } else if (password == null || password.trim().isEmpty()) {
            return false;
        } else if (confirmPassword == null || confirmPassword.isEmpty()) {
            return false;
        }
        return true;
    }
    //check if the username and password is filled
    public static boolean isFilled(String username, String password) {
        if (username == null || username.isEmpty()) {
            return false;
        } else if (password == null || password.trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
