package com.tafakkoor.englishlearningplatform.servlets.teacher.question;

import com.tafakkoor.englishlearningplatform.service.TeacherService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteQuestionServlet", value = "/teacher/grammar/delete-question/*")
public class DeleteQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/teacher/grammar/delete-question.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getPathInfo().substring(1);
        try {
            int id = Integer.parseInt(questionId);
            TeacherService teacherService = TeacherService.getInstance();
            teacherService.deleteQuestion(id);
            response.sendRedirect("/teacher/grammar/list");
        } catch (NumberFormatException e) {
            response.sendRedirect("/teacher/grammar/list");
        }
    }
}
