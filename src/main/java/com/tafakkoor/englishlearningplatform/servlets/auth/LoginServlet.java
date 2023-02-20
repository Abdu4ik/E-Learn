package com.tafakkoor.englishlearningplatform.servlets.auth;

import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import com.tafakkoor.englishlearningplatform.domains.Users;
import com.tafakkoor.englishlearningplatform.service.UserService;
import com.tafakkoor.englishlearningplatform.utils.AES;
import com.tafakkoor.englishlearningplatform.utils.Encrypt;
import com.tafakkoor.englishlearningplatform.utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/authorization/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getQueryString();


        String username = request.getParameter("in_username");
        String password = request.getParameter("in_password");

        Users user = UserDAO.getInstance().findByUsername(username);

        if (user == null) {
            request.setAttribute("error", "Username or password is incorrect");
            request.getRequestDispatcher("/views/authorization/login.jsp").forward(request, response);
        } else {
            if (UserService.isCorrectPassword(password, user.getPassword())) {

                String userId = String.valueOf(user.getId());
                String encrypt = AES.encrypt(userId);
                Cookie cookie = UserService.createCookie(encrypt);
                response.addCookie(cookie);
                response.sendRedirect((url != null)? url.substring(5) : Utils.redirectByRole(user));
            } else {
                request.setAttribute("error", "Username or password is incorrect");
                request.getRequestDispatcher("/views/authorization/login.jsp").forward(request, response);
            }
        }
    }
}
