package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Grammar;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;


public class GrammarDAO extends BaseDAO<Grammar, Integer> {

    public List<Grammar> getPage(int page, int size) {
        begin();
        TypedQuery<Grammar> query = em.createQuery("from Grammar ", Grammar.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        commit();
        return query.getResultList();
    }



    public List<Grammar> findAllStories() {
        begin();
        TypedQuery<Grammar> query = em.createQuery("from Grammar s where s.deleted=false order by createdAt desc", Grammar.class);
        commit();
        return query.getResultList();
    }

    public List<Grammar> findAllGrammars() {
        begin();
        TypedQuery<Grammar> query = em.createQuery("from Grammar s where s.deleted=false order by createdAt desc", Grammar.class);
        commit();
//        System.out.println();
        return query.getResultList();

    }
    public Grammar getStoryWithOption( String userLevel) {
        begin();
        Grammar grammar =(Grammar) em.createNativeQuery("select * from grammar where level =:userLevel", Grammar.class)
                .setParameter("userLevel", userLevel).getSingleResult();
        commit();
        return grammar;
    }

    public static GrammarDAO getInstance() {
        return new GrammarDAO();
    }
}
