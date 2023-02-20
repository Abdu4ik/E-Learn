package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Story;
import com.tafakkoor.englishlearningplatform.domains.Vocabulary;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VocabularyDAO extends BaseDAO<Vocabulary, Integer> {
    public static final ThreadLocal<VocabularyDAO> vocabularyDAOThreadLocal = ThreadLocal.withInitial(VocabularyDAO::new);

    public static VocabularyDAO getInstance() {
        return vocabularyDAOThreadLocal.get();
    }

    public List<Vocabulary> getVocabulariesByStoryId(Story storyId) {
        begin();
        TypedQuery<Vocabulary> query = em.createQuery("from Vocabulary v where v.story = :storyId", Vocabulary.class);
        query.setParameter("storyId", storyId);
        commit();
        return query.getResultList();
    }


}
