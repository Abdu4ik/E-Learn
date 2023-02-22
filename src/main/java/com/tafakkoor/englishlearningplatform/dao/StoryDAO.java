package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Story;
import com.tafakkoor.englishlearningplatform.domains.Users;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
public class StoryDAO extends BaseDAO<Story, Integer> {

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

//    public boolean delete( Integer id ) {
//        begin();
//        em.createNativeQuery("update story set deleted = true where id = :id;").setParameter("id", id);
//        commit();
//        return true;
//    }




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
    public boolean delete( Integer id ) {
        begin();
        em.createNativeQuery("update story set deleted = true where id = :id;").setParameter("id", id);
        commit();
        return true;
    }
    public Story getStoryWithOption( String userLevel, Long userId ) {

        begin();
        Query query = em.createNativeQuery("select *from story where level = :level and id not in (select story_id from user_story where user_id = :userId and is_saved = true) limit 1;", Story.class)
                .setParameter("level", userLevel)
                .setParameter("userId", userId);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        Story story=(Story) query.getSingleResult();
        commit();
        return story;
    }
    public List<Story> getStoriesById(List<Integer> storyIds) {
        begin();
        TypedQuery<Story> query = em.createQuery("from Story s where s.deleted=false and s.id in :storyIds", Story.class);
        query.setParameter("storyIds", storyIds);
        commit();

        return query.getResultList();
    }

    public Story getStoryById(long storyId) {
        return em.find(Story.class, storyId);
    }

    public static StoryDAO getInstance() {
        return new StoryDAO();
    }

    public List<Story> getStoryListByUserLevel(int id) {
        Users user = new UserDAO().findById((long) id);
        if (user == null) {
            return new ArrayList<>();
        }

        begin();

        List<Story> query = em.createNativeQuery(
                "select * from story s where s.level = :level and s.id not in (select story_id from user_story where user_id = :userId and is_saved = true);", Story.class)
                .setParameter("level", user.getLevel().name())
                .setParameter("userId", id).getResultList();


        commit();
        return query;
    }

//    public static void main(String[] args) {
//
//        StoryDAO storyDAO = new StoryDAO();
//        Story storyWithOption =storyDAO.getStoryWithOption("BEGINNER", 1L);
//        System.out.println("storyWithOption = " + storyWithOption);
//        for (Story story : storyDAO.getStoryListByUserLevel(1)) {
//            System.out.println("story = " + story);
//        }
//    }
}
