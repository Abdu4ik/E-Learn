package com.tafakkoor.englishlearningplatform.servlets.auth;

import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import com.tafakkoor.englishlearningplatform.domains.Users;
import com.tafakkoor.englishlearningplatform.service.UserService;
import com.tafakkoor.englishlearningplatform.utils.AES;
import com.tafakkoor.englishlearningplatform.utils.Encrypt;
import com.tafakkoor.englishlearningplatform.utils.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/authorization/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = UserValidator.validate(request);

        if (errors.isEmpty()) {
            UserService.register(request, response);
        } else {
            errors.forEach(request::setAttribute);
            request.getRequestDispatcher("views/authorization/register.jsp").forward(request, response);
        }

    }
}
