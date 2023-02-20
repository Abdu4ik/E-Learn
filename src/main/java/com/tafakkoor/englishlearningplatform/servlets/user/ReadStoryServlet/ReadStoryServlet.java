package com.tafakkoor.englishlearningplatform.servlets.user.ReadStoryServlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ReadStoryServlet", value = "/ReadStoryServlet")
public class ReadStoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           request.getRequestDispatcher("/views/user/stories.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public static String calculateScore(String word){
        System.out.println(word);
        return word;
    }
}
