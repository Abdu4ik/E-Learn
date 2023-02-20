package com.tafakkoor.englishlearningplatform.service;

import com.tafakkoor.englishlearningplatform.dao.GrammarDAO;
import com.tafakkoor.englishlearningplatform.dao.StoryDAO;
import com.tafakkoor.englishlearningplatform.dao.VocabularyDAO;
import com.tafakkoor.englishlearningplatform.domains.Grammar;
import com.tafakkoor.englishlearningplatform.domains.Story;
import com.tafakkoor.englishlearningplatform.domains.Vocabulary;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.lang.ThreadLocal.withInitial;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TeacherService {
    public static final ThreadLocal<TeacherService> instance = ThreadLocal.withInitial(TeacherService::new);


    public static TeacherService getInstance() {
        return instance.get();
    }

    public Story getStoryById(int id) {
        return StoryDAO.getInstance().findById(id);
    }

    public List<Vocabulary> getVocabulariesByStory(int id) {
        return VocabularyDAO.getInstance().getVocabulariesByStoryId(getStoryById(id));
    }

    public List<Story> getAllStories() {
        return StoryDAO.getInstance().findAllStories();
    }

    public List<Grammar> getAllGrammars() {
        return GrammarDAO.getInstance().findAllGrammars();
    }
}
