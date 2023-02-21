package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Story;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
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
    @Override
    public boolean delete( Story story ) {
        delete(story.getId());
        return true;
    }

    public boolean delete( Integer id ) {
        begin();
        em.createNativeQuery("update story set deleted = true where id = :id;").setParameter("id", id);
        commit();
        return true;
    }


    public static StoryDAO getInstance() {
        return storyDAOThreadLocal.get();
    }


    public Story getStoryWithOption( String userLevel, Integer userId ) {
        begin();
        Story story =(Story) em.createNativeQuery("select *\n" +
                        "from story\n" +
                        "where level = :level\n" +
                        "  and id not in (select story_id from user_story where user_id = :userId and is_saved = true);", Story.class)
                .setParameter("level", userLevel)
                .setParameter("userId", userId).getSingleResult();
        commit();
        return story;
    }


    public List<Story> findAllStories() {
        begin();
        TypedQuery<Story> query = em.createQuery("from Story s where s.deleted=false order by createdAt desc", Story.class);
        commit();
        return query.getResultList();
    }
}
