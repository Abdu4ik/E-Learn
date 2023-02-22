package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Story;
import com.tafakkoor.englishlearningplatform.domains.Vocabulary;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

public class VocabularyDAO extends BaseDAO<Vocabulary, Integer> {


    public List<Vocabulary> getVocabulariesByStoryId(Story storyId) {
        begin();
        TypedQuery<Vocabulary> query = em.createQuery("from Vocabulary v where v.story = :storyId", Vocabulary.class);
        query.setParameter("storyId", storyId);
        commit();
        return query.getResultList();
    }
    public Integer getVocabularyCountWithOption(Integer userId,Integer storyId) {
        begin();
        TypedQuery<Vocabulary> query = em.createQuery("from Vocabulary v where v.deleted=false and v.createdBy = :userId and v.story.id = :storyId", Vocabulary.class);
        query.setParameter("userId", userId);
        query.setParameter("storyId", storyId);
        commit();
        return query.getResultList().size();
    }

    public static VocabularyDAO getInstance() {
        return new VocabularyDAO();
    }


    public boolean hasWord(String eWord, String uWord) {
        begin();
        TypedQuery<Vocabulary> query = em.createQuery("from Vocabulary v where v.word=:eWord and v.meaning=:uWord", Vocabulary.class);
        query.setParameter("eWord" ,eWord);
        query.setParameter("uWord" ,uWord);
        commit();
        int size = query.getResultList().size();
        return size>0;
    }
}
