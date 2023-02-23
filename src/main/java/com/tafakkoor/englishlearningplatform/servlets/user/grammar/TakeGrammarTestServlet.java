
package com.tafakkoor.englishlearningplatform.servlets.user.grammar;

import com.google.gson.Gson;
import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import com.tafakkoor.englishlearningplatform.dao.UserTestHistoryDao;
import com.tafakkoor.englishlearningplatform.domains.QuizPostHelper;
import com.tafakkoor.englishlearningplatform.domains.Users;
import com.tafakkoor.englishlearningplatform.domains.UsersTestsHistory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "TakeGrammarTestServlet", value = "/grammar/test")
public class TakeGrammarTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String userId = request.getSession().getAttribute("user_id").toString();
        request.setAttribute("userId", userId);
        Integer grammarId = Integer.parseInt(String.valueOf(request.getParameter("grammarId")));

        request.setAttribute("grammarId", grammarId);

        request.getRequestDispatcher("/views/user/take_grammar_test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

        System.out.println("requestBody = " + requestBody);
        Gson gson = new Gson();
        QuizPostHelper qph = gson.fromJson(requestBody, QuizPostHelper.class);
        System.out.println("qph = " + qph);
        Integer userId = qph.getUserId();
        System.out.println("userId = " + userId);
        Users user = UserDAO.getInstance().findById(Long.valueOf(userId));
        Integer lastTestID = user.getLastTestID();
        System.out.println("lastTestID = " + lastTestID);
        System.out.println("qph.isCorrect() = " + qph.isCorrect());
        System.out.println("qph.getQuestionId() = " + qph.getQuestionId());
        Integer score = 0;
        if (qph.isCorrect()) {
            score = score + 1;
            System.out.println("qph.isCorrect() = " + qph.isCorrect());
            UserDAO.getInstance().updateScore(userId, user.getScore() + 1);
        }
        UserTestHistoryDao.getInstance().save(UsersTestsHistory.builder()
                .test_id(lastTestID + 1)
                .user_id(userId)
                .is_correct(qph.isCorrect())
                .question_id(qph.getQuestionId())
                .score(score)
                .build());
        if (qph.isLastQuestion()) {
            System.out.println("qph.isLastQuestion() = " + qph.isLastQuestion());
            UserDAO.getInstance().updateLastTestID(userId, lastTestID + 1);
        }
    }

}

