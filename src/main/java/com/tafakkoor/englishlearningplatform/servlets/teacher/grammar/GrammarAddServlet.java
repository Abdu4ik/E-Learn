package com.tafakkoor.englishlearningplatform.servlets.teacher.grammar;

import com.tafakkoor.englishlearningplatform.dao.GrammarDAO;
import com.tafakkoor.englishlearningplatform.domains.Document;
import com.tafakkoor.englishlearningplatform.domains.Grammar;
import com.tafakkoor.englishlearningplatform.enums.Levels;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "GrammarAddServlet", value = "/teacher/grammar/add")
@MultipartConfig(location = "c:\\pdp\\BOOTCAMP\\jakarta\\E-Learn\\src\\main\\resources\\files")
public class GrammarAddServlet extends HttpServlet {
    public static final String projectPath = System.getProperty("user.dir");
    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/apps/library/upload");

    @Override
    public void init() throws ServletException {
        if (!Files.exists(rootPath)) {
            try {
                Files.createDirectories(rootPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Levels> levels = List.of(Levels.values());
        request.setAttribute("levels", levels);
        request.getRequestDispatcher("/views/teacher/grammar/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 2/19/2023 avval filterda tekshirish kerak
        String title = request.getParameter("title");
        String score = request.getParameter("score");
        String level = request.getParameter("level");

        Part file = request.getPart("file");
        String originalName = file.getSubmittedFileName();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String generatedName = UUID.randomUUID() + extension;
        Document document = Document.builder()
                .generatedFileName(generatedName)
                .originalFileName(originalName)
                .filePath(rootPath.resolve(generatedName).toString())
                .createdBy(1) // TODO: 2/16/2023 admin id ni qo'shish kerak
                .build();
        file.write(generatedName);
        Grammar grammar = Grammar.builder()
                .title(title)
                .score(Integer.parseInt(score))
                .level(Levels.valueOf(level))
                .document(document)
                .createdBy(1) // TODO: 2/16/2023 admin id ni qo'shish kerak
                .build();
        GrammarDAO.getInstance().save(grammar);
        response.sendRedirect("/teacher/grammar/list");


        // TODO: 2/19/2023 questionni ham saqlash kerak
    }
}
