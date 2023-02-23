package com.tafakkoor.englishlearningplatform.filters;

import com.tafakkoor.englishlearningplatform.utils.validator.UserValidator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "ProfilePageFilter", urlPatterns = {"/profile"})
public class ProfilePageFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String username = req.getParameter("username");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        if (req.getMethod().equalsIgnoreCase("post")) {
            Map<String, String> errors = new HashMap<>();
            if (username == null || username.isBlank() || username.isEmpty()) {
                errors.put("username_error", "Username can not be null");
            } else if (UserValidator.isDuplicateUsername(username)) {
                errors.put("username_error", "Username already taken!");
            }

            if (firstname == null || firstname.isBlank() || firstname.isEmpty()) {
                errors.put("pass_conf_err", "Password can not be null");
            }

            if (lastname == null || lastname.isBlank() || lastname.isEmpty()) {
                errors.put("pass_conf_err", "Lastname can not be null");
            }

            if (email == null || email.isBlank() || email.isEmpty()) {
                errors.put("email_error", "Email can not be null");
            } else if (UserValidator.isDuplicateEmail(email)) {
                errors.put("email_error", "Email already taken");
            }

            if (errors.isEmpty()) {
                chain.doFilter(request, response);
            } else {
                UserValidator.setErrorAttributes(req, errors);
                req.getRequestDispatcher("views/authorization/register.jsp").forward(req, res);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
