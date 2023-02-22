
package com.tafakkoor.englishlearningplatform.servlets.user;

import com.tafakkoor.englishlearningplatform.dao.DocumentDAO;
import com.tafakkoor.englishlearningplatform.dao.GrammarDAO;
import com.tafakkoor.englishlearningplatform.dao.StoryDAO;
import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import com.tafakkoor.englishlearningplatform.domains.Grammar;
import com.tafakkoor.englishlearningplatform.domains.Users;
import com.tafakkoor.englishlearningplatform.enums.Levels;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet( name = "GrammarServlet", value = "/grammar" )
public class GrammarServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        //        HttpSession session = request.getSession();
        GrammarDAO grammarDAO = GrammarDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        DocumentDAO documentDAO = new DocumentDAO();
        try {
            Integer userId = (Integer) 3;//session.getAttribute("user_id");
            Users byIdUser = userDAO.findById(Long.valueOf(userId));
            Levels userLevel = byIdUser.getLevel();
            Grammar grammarWithOption = grammarDAO.getStoryWithOption(userLevel.name());
            request.setAttribute("userId",userId);
           // Document byIdDoc = documentDAO.findById(grammarWithOption.getDocument().getId());
           // request.setAttribute("file",byIdDoc.getFilePath());
            request.setAttribute("grammarId",grammarWithOption.getId());
            request.getRequestDispatcher("/views/user/grammar.jsp").forward(request, response);
        } catch ( NumberFormatException e ) {
            System.out.println("Num exception");
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        StoryDAO storyDAO = StoryDAO.getInstance();
        try {
            int grammarId = Integer.parseInt(request.getParameter("grammarId"));
            System.out.println(grammarId);
            int userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println(userId);

        }catch ( NumberFormatException e ){
            System.out.println("NumberFormat Exception!");
        }


    }

}