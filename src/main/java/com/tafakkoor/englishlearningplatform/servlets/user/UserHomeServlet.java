package com.tafakkoor.englishlearningplatform.servlets.user;

import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserHomServlet", value = "/user")
public class UserHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        if (userId != null){
            String userLevel = UserDAO.getInstance().findById(Long.parseLong(userId)).getLevel().toString();
            request.getSession().setAttribute("userLevel", userLevel);
        }


        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
