package com.tafakkoor.englishlearningplatform.servlets.user.grammar;

import com.tafakkoor.englishlearningplatform.domains.Grammar;
import com.tafakkoor.englishlearningplatform.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GrammarListPageServlet", value = "/grammar-list-page")
public class GrammarListPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        List<Grammar>grammars= UserService.getInstance().getGrammarListByUserLevel(userId);
        request.setAttribute("grammars", grammars);

        request.getRequestDispatcher("/views/user/grammar-list-page.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
