package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Questions;
import com.tafakkoor.englishlearningplatform.domains.Story;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class QuestionDAO extends BaseDAO<Questions, Integer> {


    private static final ThreadLocal<QuestionDAO> questionDAOThreadLocal = ThreadLocal.withInitial(QuestionDAO::new);



    public List<Questions> findAllByGrammarId( Integer id ) {
        begin();
        List<Questions> questionsList = em.createNativeQuery("select * from questions where grammar_id = :id", Questions.class).setParameter("id", id).getResultList();
        commit();
        return questionsList;
    }


    public static QuestionDAO getInstance() {
        return questionDAOThreadLocal.get();
    }
}
