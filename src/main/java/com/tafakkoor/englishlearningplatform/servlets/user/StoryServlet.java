package com.tafakkoor.englishlearningplatform.servlets.user;

import com.tafakkoor.englishlearningplatform.dao.*;
import com.tafakkoor.englishlearningplatform.domains.*;
import com.tafakkoor.englishlearningplatform.enums.Levels;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ReadStoryServlet", value = "/reading")
public class StoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
        StoryDAO storyDAO = StoryDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        DocumentDAO documentDAO = new DocumentDAO();
        VocabularyDAO vocabularyDAO = VocabularyDAO.getInstance();
        try {
            Integer userId = (Integer) 3;//session.getAttribute("user_id");
            Users byIdUser = userDAO.findById(Long.valueOf(userId));
            Levels userLevel = byIdUser.getLevel();
            Story storyWithOption = storyDAO.getStoryWithOption(userLevel.name(), userId);

            List<Vocabulary> allByVocabulary = vocabularyDAO.getVocabulariesByStoryId(storyWithOption);
            request.setAttribute("vocabularyList", allByVocabulary);
            request.setAttribute("storyId", storyWithOption.getId());
            request.setAttribute("userId", userId);
            Document byIdDoc = documentDAO.findById(storyWithOption.getDocument().getId());
            request.setAttribute("file", byIdDoc.getFilePath());
            request.getRequestDispatcher("/views/user/stories.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoryDAO storyDAO = StoryDAO.getInstance();
        try {

            int storyId = Integer.parseInt(request.getParameter("storyId"));
            System.out.println(storyId);
            int userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println(userId);
            String feedback = request.getParameter("feedback");
            String e_word = request.getParameter("eword");
            String u_word = request.getParameter("uword");
            Story story = storyDAO.findById(storyId);
            if (Objects.nonNull(e_word) && Objects.nonNull(u_word) && !e_word.equals("") && !u_word.equals("")) {
                VocabularyDAO vocabularyDAO = VocabularyDAO.getInstance();
                vocabularyDAO.save(Vocabulary.builder()
                        .story(story)
                        .word(e_word)
                        .meaning(u_word)
                        .createdBy(userId)
                        .build());
            }
            System.out.println(feedback);
            Story byIdStory = story;
            UserStoryDAO userStoryDAO = UserStoryDAO.getInstance();
            userStoryDAO.save(User_Story.builder()
                    .story_id(storyId)
                    .user_id(userId)
                    .is_saved(true)
                    .build());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }



}
