package com.tafakkoor.englishlearningplatform;

import com.tafakkoor.englishlearningplatform.domains.User_Story;

public class Main {
    public static void main(String[] args) {
   String name= "hello";
        User_Story.builder().id(111).user_id(111).story_id(111).grammar_id(111).is_saved(true).build();
    }
}
