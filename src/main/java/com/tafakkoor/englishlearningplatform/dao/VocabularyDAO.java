package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Vocabulary;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VocabularyDAO extends BaseDAO<Vocabulary, Integer>{
    public static final ThreadLocal<VocabularyDAO> vocabularyDAOThreadLocal = ThreadLocal.withInitial(VocabularyDAO::new);

    public static VocabularyDAO getInstance() {
        return vocabularyDAOThreadLocal.get();
    }
}
