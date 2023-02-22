package com.tafakkoor.englishlearningplatform.service;

import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import com.tafakkoor.englishlearningplatform.domains.Users;
import com.tafakkoor.englishlearningplatform.domains.VocabHelperTest;
import com.tafakkoor.englishlearningplatform.domains.Vocabulary;
import com.tafakkoor.englishlearningplatform.utils.Encrypt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService {
    public static final int minutes = 60;
    public static final int hours = 3600;
    public static final int oneDay = 86400;

    public static Cookie createCookie(String value) {
        Cookie cookie = new Cookie("remember_me", value);
        cookie.setPath("/");
        cookie.setMaxAge(oneDay);
        return cookie;
    }

    public static void register(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Users newUser = Users.builder()
                .email(email)
                .username(username)
                .password(Encrypt.hashPassword(password))
                .build();

        UserDAO.getInstance().save(newUser);
        response.sendRedirect("/login");
    }

    public static boolean isCorrectPassword(String password, String hashedPassword) {
        return Encrypt.checkPassword(password, hashedPassword);
    }

    public static boolean isCorrectPass(String userId, String password) {
        Users user = null;
        try {
            user = UserDAO.getInstance().findById(Long.parseLong(userId));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return isCorrectPassword(password, user.getPassword());
    }

    public static void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        String newPassword = request.getParameter("new_password");

        UserDAO instance = UserDAO.getInstance();
        Users user = instance.findById(Long.parseLong(userId));
        user.setPassword(Encrypt.hashPassword(newPassword));
        instance.update(user);
        response.sendRedirect("/logout");
    }

    public static List<VocabHelperTest> getQuiz(List<Vocabulary> vocabulariesByStoryId, int storyId, List<VocabHelperTest> quizHelperList) {

        for (int i = 0; i <vocabulariesByStoryId.size() ; i++)  {
            List<String> vocabularyList = new ArrayList<>();
            vocabulariesByStoryId.remove(vocabulariesByStoryId.get(i));
            for (Vocabulary vocab : vocabulariesByStoryId) {
                vocabularyList.add(vocab.getMeaning());
            }
            Collections.shuffle(vocabularyList);
            vocabularyList = vocabularyList.subList(0, 3);
            vocabularyList.add(vocabulariesByStoryId.get(i).getMeaning());
            Collections.shuffle(vocabularyList);
            vocabulariesByStoryId.add(vocabulariesByStoryId.get(i));
            VocabHelperTest vocabHelperTest = VocabHelperTest.builder().
                    vocabulary(vocabulariesByStoryId.get(i).getWord()).
                    story_id(String.valueOf(storyId)).
                    a(vocabularyList.get(0)).b(vocabularyList.get(1)).c(vocabularyList.get(2)).d(vocabularyList.get(3)).
                    correct(vocabulariesByStoryId.get(i).getMeaning()).build();
            quizHelperList.add(vocabHelperTest);
            System.out.println(quizHelperList);
        }
        return quizHelperList;
    }
}
