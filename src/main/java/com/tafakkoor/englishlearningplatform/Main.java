package com.tafakkoor.englishlearningplatform;

import com.google.gson.Gson;
import com.tafakkoor.englishlearningplatform.domains.QuizHelper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String s = gson.toJson(List.of(QuizHelper.builder().question("What is your name?").a("My name is John").b("My name is Jane").c("My name is Jack").d("My name is Jill").correct("My name is John").build(),QuizHelper.builder().question("What is your surname?").build()));
        System.out.println(s);
    }
}
