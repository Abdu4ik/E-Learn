package com.tafakkoor.englishlearningplatform.servlets.user.story;

import com.tafakkoor.englishlearningplatform.domains.Story;
import com.tafakkoor.englishlearningplatform.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReadingListPageServlet", value = "/reading-list-page")
public class ReadingListPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        List<Story> stories= UserService.getInstance().getStoryListByUserLevel(userId);
        request.setAttribute("stories", stories);

        request.getRequestDispatcher("/views/user/reading-list-page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
