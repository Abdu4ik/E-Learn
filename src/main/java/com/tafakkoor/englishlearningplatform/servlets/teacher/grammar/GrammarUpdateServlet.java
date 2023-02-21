package com.tafakkoor.englishlearningplatform.servlets.teacher.grammar;

import com.tafakkoor.englishlearningplatform.dao.GrammarDAO;
import com.tafakkoor.englishlearningplatform.domains.Document;
import com.tafakkoor.englishlearningplatform.domains.Grammar;
import com.tafakkoor.englishlearningplatform.domains.Questions;
import com.tafakkoor.englishlearningplatform.enums.Levels;
import com.tafakkoor.englishlearningplatform.service.TeacherService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@WebServlet(name = "GrammarUpdateServlet", value = "/teacher/grammar/update/*")
@MultipartConfig(location = "c:\\pdp\\BOOTCAMP\\jakarta\\E-Learn\\src\\main\\resources\\files")
public class GrammarUpdateServlet extends HttpServlet {
    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/apps/library/upload");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String grammarId = request.getPathInfo().substring(1);
        try {
            int id = Integer.parseInt(grammarId);
            Grammar grammar = TeacherService.getInstance().getGrammarById(id);

            TeacherService teacherService = TeacherService.getInstance();
            List<Questions> questions = teacherService.getGrammarQuestions(grammar);
            request.setAttribute("questions", questions);

            request.setAttribute("grammar", grammar);
            List<Levels> levels = List.of(Levels.values());
            request.setAttribute("levels", levels);
            request.getRequestDispatcher("/views/teacher/grammar/update.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("/teacher/grammar");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String grammarId = request.getPathInfo().substring(1);
        int id = Integer.parseInt(grammarId);
        TeacherService teacherService = TeacherService.getInstance();
        Grammar grammar = teacherService.getGrammarById(id);

        String title = request.getParameter("title");
        String score = request.getParameter("score");
        String level = request.getParameter("level");
        Part file = request.getPart("file");

        Document document =null;
        if (file!=null && file.getSize() > 0) {
            String originalName = file.getSubmittedFileName();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String generatedName = UUID.randomUUID() + extension;
            Document.builder()
                    .generatedFileName(generatedName)
                    .originalFileName(originalName)
                    .filePath(rootPath.resolve(generatedName).toString())
                    .createdBy(1) // TODO: 2/16/2023 admin id ni qo'shish kerak
                    .build();
            file.write(generatedName);
        }

        grammar.setTitle(Objects.requireNonNullElse(title, grammar.getTitle()));
        grammar.setScore(Objects.requireNonNullElse(Integer.valueOf(score), grammar.getScore()));
        grammar.setLevel(Objects.requireNonNullElse(Levels.valueOf(level), grammar.getLevel()));
        grammar.setDocument(Objects.requireNonNullElse(document, grammar.getDocument()));
        grammar.setCreatedBy(Objects.requireNonNullElse(1, grammar.getCreatedBy())); // TODO: 2/16/2023 admin id ni qo'shish kerak

        teacherService.updateGrammar(grammar);

        response.sendRedirect("/teacher/grammar/list");
    }
}
