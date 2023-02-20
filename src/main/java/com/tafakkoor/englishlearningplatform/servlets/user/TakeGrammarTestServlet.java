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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TakeGrammarTestServlet", value = "/grammar/test")
public class TakeGrammarTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        Integer userId = Integer.parseInt(AES.decrypt(cookie.getValue()));
       // request.setAttribute("userId", userId);
      //  Integer grammarId=Integer.parseInt(String.valueOf(request.getAttribute("grammarId")));
        request.setAttribute("grammarId", "1");
        request.getRequestDispatcher("/views/user/take_grammar_test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // get request body
//        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
//        System.out.println(requestBody);
        String grammarId = request.getParameter("grammarId");
        HttpSession session = request.getSession();
//        Integer userId = Integer.parseInt(AES.decrypt(cookie.getValue()));
   //     String userId = request.getParameter("userId");
        String choice = request.getParameter("choice");
        String questionId = request.getParameter("questionId");
        System.out.println("grammar id:"+grammarId+"choice: "+choice+"questionId: "+questionId);
        request.setAttribute("grammarId", grammarId);
  //      request.setAttribute("userId", userId);
        request.getRequestDispatcher("/views/user/take_grammar_test.jsp").forward(request, response);
    }

}
