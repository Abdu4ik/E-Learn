package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Story;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
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


    public List<Story> findAllStories() {
        begin();
        TypedQuery<Story> query = em.createQuery("from Story s where s.deleted=false order by createdAt desc", Story.class);
        commit();
        return query.getResultList();
    }
}
