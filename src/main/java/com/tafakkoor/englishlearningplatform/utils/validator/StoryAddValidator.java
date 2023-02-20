package com.tafakkoor.englishlearningplatform.utils.validator;

import com.tafakkoor.englishlearningplatform.enums.Levels;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StoryAddValidator {
    private static StoryAddValidator instance;

    public static StoryAddValidator getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (StoryAddValidator.class) {
                if (Objects.isNull(instance)) {
                    instance = new StoryAddValidator();
                }
            }
        }
        return instance;
    }

    public static final List<Levels> LEVELS = List.of(Levels.values());

    public void validate(HttpServletRequest request) throws Exception {


        String[] enOptions = request.getParameterValues("en_options[]");
        String[] uzOptions = request.getParameterValues("uz_options[]");


        System.out.println("enOptions = " + Arrays.toString(enOptions));
        System.out.println("uzOptions = " + Arrays.toString(uzOptions));
        checkOptions(uzOptions, enOptions);

        String level = request.getParameter("level");
        checkParam(level);
        checkParam(request.getParameter("title"));
        checkParam(request.getParameter("author"));
        boolean flag = false;
        for (int i = 0; i < LEVELS.size(); i++) {
            if (LEVELS.get(i).name().equalsIgnoreCase(level)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new Exception("Level is not valid");
        }
        String score = request.getParameter("score");
        checkParam(score);
        try {
            int point = Integer.parseInt(score);
            if (point < 0 || point > 5) {
                throw new Exception("Score must be between 0 and 5");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Score must be a number");
        }
        Part file = request.getPart("file");
        if (file.getSize() > 0) {
            String fileName = file.getSubmittedFileName();
            if (!fileName.endsWith(".pdf")) {
                throw new Exception("File must be a pdf");
            }
        } else {
            throw new Exception("File is empty");
        }
    }

    private void checkParam(String param) throws Exception {
        if (Objects.isNull(param) || param.isEmpty() || param.isBlank()) {
            throw new Exception("Param is null or empty");
        }
    }
    private void checkOptions(String[] uzOptions, String[] enOptions) throws Exception {
        if (Objects.isNull(uzOptions) || Objects.isNull(enOptions) || uzOptions.length != enOptions.length) {
            throw new Exception("Options are null or does not match");
        }

        for (int i = 0; i < uzOptions.length; i++) {
            if (Objects.isNull(uzOptions[i]) ||
                    Objects.isNull(enOptions[i]) ||
                    uzOptions[i].isEmpty() ||
                    enOptions[i].isEmpty() ||
                    uzOptions[i].isBlank() ||
                    enOptions[i].isBlank()) {
                throw new Exception("Options are null or empty");
            }
        }
    }
}