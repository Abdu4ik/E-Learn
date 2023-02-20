package com.tafakkoor.englishlearningplatform.servlets.user;

import com.google.gson.Gson;
import com.tafakkoor.englishlearningplatform.dao.QuestionDAO;
import com.tafakkoor.englishlearningplatform.dao.VariantDAO;
import com.tafakkoor.englishlearningplatform.domains.Questions;
import com.tafakkoor.englishlearningplatform.domains.QuizHelper;
import com.tafakkoor.englishlearningplatform.domains.Variants;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.boot.MetadataBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet", value = "/questions/get/*")
public class QuestionGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDAO questionDAO = QuestionDAO.getInstance();
        VariantDAO variantDAO = VariantDAO.getInstance();
        String pathInfo = request.getPathInfo();
        long grammarId = Long.parseLong(pathInfo.substring(1));
        try {
            int id = Integer.parseInt(String.valueOf(grammarId));
            List<QuizHelper> quizHelperList = new ArrayList<>();
            List<Questions> questionsList = questionDAO.findAllByGrammarId(id);
            for (Questions questions : questionsList) {
                List<Variants> variants = variantDAO.findAllByQuestionId(questions.getId());
                QuizHelper quiz = QuizHelper.builder().
                        question(questions.getTitle()).
                        questionId(questions.getId()).
                        a(variants.get(0).getVariant()).
                        b(variants.get(1).getVariant()).
                        c(variants.get(2).getVariant()).
                        d(variants.get(3).getVariant()).
                        correct(getCorrectValue(variants)).build();
                quizHelperList.add(quiz);
            }
            Gson gson = new Gson();
            String jsonData = gson.toJson(quizHelperList);
            response.setContentType("application/json");
            response.getWriter().println(jsonData);
        } catch (NumberFormatException e) {
            System.out.println("Number format exception!");
        }
    }

    private String getCorrectValue(List<Variants> variants) {
        var a = variants.get(0).getVariant();
        var b = (variants.get(1).getVariant());
        var c = (variants.get(2).getVariant());
        var d = (variants.get(3).getVariant());
        var correct = variants.stream().filter(Variants::isCorrect).findFirst().get().getVariant();
        if (correct.equals(a)) {
            return "a";
        } else if (correct.equals(b)) {
            return "b";
        } else if (correct.equals(c)) {
            return "c";
        } else if (correct.equals(d)) {
            return "d";
        }
        return "";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
