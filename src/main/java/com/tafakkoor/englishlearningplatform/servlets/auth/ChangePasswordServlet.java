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

@WebServlet(name = "ChangePasswordServlet", value = "/change-password")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        response.setBufferSize(1024 * 16);
        Long userId = Long.parseLong((session.getAttribute("user_id").toString()));
        Users user = UserDAO.getInstance().findById(userId);
        request.setAttribute("fullname", user.getFirstName() + " " + user.getLastName());
//        request.setAttribute("user", user);
        request.setAttribute("user_id", user.getId());
        try {
            request.getRequestDispatcher("views/authorization/change-password.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserService.changePassword(request, response);
        try {
            String userId = request.getParameter("user_id");
            String newPassword = request.getParameter("new_password");

            UserDAO instance = UserDAO.getInstance();
            Users user = instance.findById(Long.parseLong(userId));
            user.setPassword(Encrypt.hashPassword(newPassword));
            instance.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("success_message", "Password updated successfully");
//            response.sendRedirect("logout");
            request.getRequestDispatcher("views/authorization/change-password.jsp").forward(request, response);
        }

    }
}
