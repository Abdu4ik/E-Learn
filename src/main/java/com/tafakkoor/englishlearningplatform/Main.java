package com.tafakkoor.englishlearningplatform;

import com.google.gson.Gson;
import com.tafakkoor.englishlearningplatform.domains.QuizHelper;
import com.tafakkoor.englishlearningplatform.service.UserService;
import com.tafakkoor.englishlearningplatform.utils.validator.UserValidator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(UserValidator.validateUsername("Fayzulloxqwdqw"));
    }
}
