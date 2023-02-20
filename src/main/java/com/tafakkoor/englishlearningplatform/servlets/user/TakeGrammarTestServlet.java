package com.tafakkoor.englishlearningplatform.servlets.user;

import com.tafakkoor.englishlearningplatform.dao.QuestionDAO;
import com.tafakkoor.englishlearningplatform.dao.VariantDAO;
import com.tafakkoor.englishlearningplatform.domains.Questions;
import com.tafakkoor.englishlearningplatform.domains.Variants;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "TakeGrammarTestServlet", value = "/grammar/test" )
public class TakeGrammarTestServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        QuestionDAO questionDAO = QuestionDAO.getInstance();
        VariantDAO variantDAO = VariantDAO.getInstance();
        String grammarId = request.getParameter("grammarId");

        List<Variants> variantList = new ArrayList<>();
        try {
            int id = Integer.parseInt(grammarId);
            List<Questions> questionsList = questionDAO.findAllByGrammarId(id);
            request.setAttribute("grammarId",grammarId);
            for ( Questions questions : questionsList ) {
                for ( Variants variants : variantDAO.findAllByQuestionId(questions.getId()) ) {
                    variantList.add(variants);
                }
            }
            request.setAttribute("questionList", questionsList);
            request.setAttribute("variantList",variantList);
            request.getRequestDispatcher("/views/user/take_grammar_test.jsp").forward(request, response);
        } catch ( NumberFormatException e ) {
            System.out.println("Numberformat exception!");
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        QuestionDAO questionDAO = QuestionDAO.getInstance();
        String grammarId = request.getParameter("grammarId");
        List<Questions> allQuestions = questionDAO.findAllByGrammarId(Integer.parseInt(grammarId));
        for ( Questions question : allQuestions ) {
            request.getParameter(String.valueOf(question.getId()));
        }
        request.getRequestDispatcher("/views/user/take_grammar_test.jsp").forward(request,response);
    }

}
