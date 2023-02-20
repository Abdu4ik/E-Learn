package com.tafakkoor.englishlearningplatform.servlets.teacher.story;

import com.tafakkoor.englishlearningplatform.dao.StoryDAO;
import com.tafakkoor.englishlearningplatform.domains.Story;
import com.tafakkoor.englishlearningplatform.domains.Vocabulary;
import com.tafakkoor.englishlearningplatform.enums.Levels;
import com.tafakkoor.englishlearningplatform.service.TeacherService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "ServletStoryUpdateServlet", value = "/teacher/story/update/*")
public class ServletStoryUpdateServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storyId = request.getPathInfo().substring(1);
        try {

            int id = Integer.parseInt(storyId);

            TeacherService teachService = TeacherService.getInstance();
            Story story = teachService.getStoryById(id);
            if (story == null) {
                response.sendError(404, "Story with id '%s' not found".formatted(storyId));
            } else {
                List<Vocabulary> vocabularyList = teachService.getVocabulariesByStory(id);
                request.setAttribute("vocabularyList", vocabularyList);
                request.setAttribute("story", story);
                List<Levels> levels = List.of(Levels.values());
                request.setAttribute("levels", levels);
                request.getRequestDispatcher("/views/teacher/story/update.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendError(404, "Story with id '%s' not found".formatted(storyId));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 2/19/2023 update qismini yozish kerak

    }
}
