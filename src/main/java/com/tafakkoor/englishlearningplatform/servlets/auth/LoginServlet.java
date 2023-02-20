package com.tafakkoor.englishlearningplatform.servlets.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/authorization/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");

        if (message.equals("login")) {
            String in_username = request.getParameter("in_username");
            String in_password = request.getParameter("in_password");
            System.out.println("""
                    Signing in
                    username: %s
                    password: %s
                    """.formatted(in_username, in_password));
        } else if (message.equals("register")) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm_password = request.getParameter("confirm_password");
            System.out.println("""
                    Signing up
                    username: %s
                    email: %s
                    password: %s
                    confirm password: %s
                    """.formatted(username, email, password, confirm_password));
        }

//        String loginUsername = request.getParameter("in_username");
//        String inPassword = request.getParameter("in_password");
//
//        String username = request.getParameter("username");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String confirmPassword = request.getParameter("confirm_password");

    }
}
