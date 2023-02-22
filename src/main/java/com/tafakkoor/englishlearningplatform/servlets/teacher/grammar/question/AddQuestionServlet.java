package com.tafakkoor.englishlearningplatform.servlets.teacher.grammar.question;

import com.tafakkoor.englishlearningplatform.domains.Grammar;
import com.tafakkoor.englishlearningplatform.domains.Questions;
import com.tafakkoor.englishlearningplatform.domains.Variants;
import com.tafakkoor.englishlearningplatform.service.TeacherService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "AddQuestionServlet", value = "/teacher/grammar/add-question/*")
public class AddQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String grammarId = request.getPathInfo().substring(1);
        try {
            Grammar grammar=TeacherService.getInstance().getGrammarById(Integer.parseInt(grammarId));
            if (Objects.isNull(grammar)){
                throw new Exception("Grammar not found");
            }
            request.setAttribute("grammar", grammar);
            request.getRequestDispatcher("/views/teacher/grammar/add-question.jsp").forward(request, response);
        }catch (Exception e){
            response.sendRedirect("/teacher/grammar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String grammarId = request.getPathInfo().substring(1);
        try {
            Grammar grammar=TeacherService.getInstance().getGrammarById(Integer.parseInt(grammarId));
            String option1 = request.getParameter("answer1");
            String option2 = request.getParameter("answer2");
            String option3 = request.getParameter("answer3");
            String option4 = request.getParameter("answer4");
            String correctAnswer = request.getParameter("correctAnswer");
            String question = request.getParameter("question");
            Questions questions = Questions.builder()
                    .grammar(grammar)
                    .title(question)
                    .build();
            TeacherService.getInstance().saveQuestion(questions);

            saveQuestionOptions(questions, correctAnswer, option1, option2, option3, option4);
            response.sendRedirect("/teacher/grammar/update/"+grammarId);
        }catch (Exception e){
            response.sendRedirect("/teacher/grammar/update/"+grammarId);
        }
    }

    private void saveQuestionOptions(Questions questions, String correctOption, String... options) {
        for (int i = 0; i < options.length; i++) {
            Variants variants = new Variants();
            variants.setQuestions(questions);
            variants.setVariant(options[i]);
            variants.setCorrect(String.valueOf(i + 1).equals(correctOption));
            TeacherService.getInstance().updateVariant(variants);
        }
    }
}
