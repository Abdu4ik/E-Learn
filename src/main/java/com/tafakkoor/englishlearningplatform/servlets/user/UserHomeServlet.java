package com.tafakkoor.englishlearningplatform.servlets.user;

import com.tafakkoor.englishlearningplatform.utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UserHomServlet", value = "/user")
public class UserHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("hasCookie", Utils.checkCookie(request));
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
