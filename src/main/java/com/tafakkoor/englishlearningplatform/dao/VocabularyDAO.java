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
        TypedQuery<Vocabulary> query = em.createQuery("from Vocabulary v where v.deleted=false and v.story = :storyId", Vocabulary.class);
        query.setParameter("storyId", storyId);
        commit();
        return query.getResultList();
    }


}
