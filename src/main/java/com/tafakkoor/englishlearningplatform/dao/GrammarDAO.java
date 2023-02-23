package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Grammar;
import com.tafakkoor.englishlearningplatform.domains.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;


public class GrammarDAO extends BaseDAO<Grammar, Integer> {

    public List<Grammar> getPage(int page, int size) {
        TypedQuery<Grammar> query;
        try (EntityManager em = emf.createEntityManager()) {
            query = em.createQuery("from Grammar ", Grammar.class);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }


    public List<Grammar> findAllStories() {
        TypedQuery<Grammar> query;
        try (EntityManager em = emf.createEntityManager()) {
            query = em.createQuery("from Grammar s where s.deleted=false order by createdAt desc", Grammar.class);
        }
        return query.getResultList();
    }

    public List<Grammar> findAllGrammars() {
        TypedQuery<Grammar> query;
        try (EntityManager em = emf.createEntityManager()) {
            query = em.createQuery("from Grammar s where s.deleted=false order by createdAt desc", Grammar.class);
        }
        return query.getResultList();

    }

    public Grammar getStoryWithOption(String userLevel) {
        Grammar grammar;
        try (EntityManager em = emf.createEntityManager()) {
            grammar = (Grammar) em.createNativeQuery("select * from grammar where level =:userLevel order by random() limit 1", Grammar.class)
                    .setParameter("userLevel", userLevel).getSingleResult();
        }
        return grammar;
    }

    public static GrammarDAO getInstance() {
        return new GrammarDAO();
    }

    public List<Grammar> getGrammarListByUserLevel(long id) {
        Users user = new UserDAO().findById(id);
        if (user == null) {
            return new ArrayList<>();
        }

        List<Grammar> query;
        try (EntityManager em = emf.createEntityManager()) {
            query = em.createNativeQuery(
                            "select * from grammar s where s.level = :level and s.id not in (select grammar_id from user_story where user_id = :userId);", Grammar.class)
                    .setParameter("level", user.getLevel().name())
                    .setParameter("userId", id).getResultList();
        }
        return query;
    }
}
