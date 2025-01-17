package com.tafakkoor.englishlearningplatform.filters.grammar;

import com.tafakkoor.englishlearningplatform.enums.Levels;
import com.tafakkoor.englishlearningplatform.service.TeacherService;
import com.tafakkoor.englishlearningplatform.utils.validator.StoryAddValidator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;

@Log
@WebFilter(filterName = "StoryFilter", urlPatterns = {"/teacher/grammar/add"})
@MultipartConfig
public class GrammarFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("post")) {
            try {
                TeacherService teacherService = TeacherService.getInstance();
                teacherService.validateGrammar(req);
                teacherService.validateQuestion(req);
                chain.doFilter(req, res);
            } catch (Exception e) {
                List<Levels> levels = List.of(Levels.values());
                request.setAttribute("levels", levels);
                request.getRequestDispatcher("/views/teacher/grammar/add").forward(request, response);
            }
        } else {
            List<Levels> levels = List.of(Levels.values());
            request.setAttribute("levels", levels);
            chain.doFilter(req, res);
        }
    }
}
