package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Story;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoryDAO extends BaseDAO<Story, Integer> {
    private static final ThreadLocal<StoryDAO> storyDAOThreadLocal = ThreadLocal.withInitial(StoryDAO::new);

    public List<Story> getPage(int page, int size) {
        begin();
        TypedQuery<Story> query = em.createQuery("from Story ", Story.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        commit();
        return query.getResultList();
    }

    public static StoryDAO getInstance() {
        return storyDAOThreadLocal.get();
    }


}
