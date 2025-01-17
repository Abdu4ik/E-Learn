
package com.tafakkoor.englishlearningplatform.servlets.user.grammar;

import com.tafakkoor.englishlearningplatform.dao.DocumentDAO;
import com.tafakkoor.englishlearningplatform.dao.GrammarDAO;
import com.tafakkoor.englishlearningplatform.dao.StoryDAO;
import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import com.tafakkoor.englishlearningplatform.domains.Document;
import com.tafakkoor.englishlearningplatform.domains.Grammar;
import com.tafakkoor.englishlearningplatform.domains.Users;
import com.tafakkoor.englishlearningplatform.enums.Levels;
import com.tafakkoor.englishlearningplatform.service.TeacherService;
import com.tafakkoor.englishlearningplatform.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URL;

@WebServlet( name = "GrammarServlet", value = "/grammar" )
public class GrammarServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TeacherService teacherService = TeacherService.getInstance();
        UserService userService = UserService.getInstance();
        try {
            Long userId = Long.parseLong(String.valueOf(session.getAttribute("user_id")));
            Users byIdUser = userService.findById(Long.valueOf(userId));
            Levels userLevel = byIdUser.getLevel();
            Grammar grammarWithOption = teacherService.getGrammarWithOption(userLevel.name());
            request.setAttribute("userId", userId);
            Document byIdDoc = teacherService.findGrammarById(grammarWithOption.getDocument().getId());
            URL domain = new URL("http://localhost:8080/");
            URL url = new URL(domain + byIdDoc.getFilePath());
            request.setAttribute("file", url);
            request.setAttribute("grammarId", grammarWithOption.getId());
            request.getRequestDispatcher("/views/user/grammar.jsp").forward(request, response);
        } catch ( NumberFormatException e ) {
            System.out.println("Num exception");
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        try {
            int grammarId = Integer.parseInt(request.getParameter("grammarId"));
            System.out.println(grammarId);

            request.setAttribute("grammarId", grammarId);
            request.getRequestDispatcher("/views/user/take_grammar_test.jsp").forward(request, response);
        } catch ( NumberFormatException e ) {
            System.out.println("NumberFormat Exception!");
        }


    }

}