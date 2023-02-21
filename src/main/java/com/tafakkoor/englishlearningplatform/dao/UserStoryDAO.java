
package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.User_Story;

import java.util.Objects;

public class UserStoryDAO extends BaseDAO<User_Story, Integer> {

    private static final ThreadLocal<UserStoryDAO> userStoryDAOThreadLocal = ThreadLocal.withInitial(UserStoryDAO::new);

    public boolean isSaved( Integer storyId, Integer userId ) {
        begin();
        User_Story user_story =(User_Story) em.createNativeQuery("select * from user_story where story_id = :story_id and user_id = :user_id;", User_Story.class)
                .setParameter("user_id", storyId)
                .setParameter("story_id", storyId)
                .getSingleResult();
        if( Objects.isNull(user_story)) return false;
        commit();
        return true;
    }

    public static UserStoryDAO getInstance() {
        return userStoryDAOThreadLocal.get();
    }
}
