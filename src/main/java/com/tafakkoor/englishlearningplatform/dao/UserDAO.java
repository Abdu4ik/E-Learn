package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Users;
import com.tafakkoor.englishlearningplatform.enums.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAO extends BaseDAO<Users, Long> {
    private static final ThreadLocal<UserDAO> userDAOThreadLocal = ThreadLocal.withInitial(UserDAO::new);

    public static UserDAO getInstance() {
        return userDAOThreadLocal.get();
    }

    public List<Users> getPage(int page, int size) {
        begin();
        TypedQuery<Users> query = em.createQuery("from Users ", Users.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        commit();
        return query.getResultList();
    }

    public boolean changeRole(Integer id, String path) {
        Roles role = null;
        switch (path) {
            case "admin" -> role = Roles.ADMIN;
            case "teacher" -> role = Roles.TEACHER;
            case "user" -> role = Roles.USER;
        }

        EntityManager entityManager = getInstance().em;
        entityManager.getTransaction().begin();
        int i = entityManager.createQuery("update Users set role = :role where id = :id")
                .setParameter("role", role)
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        return i > 0;

    }

    public List<Users> findAll() {
        return em.createQuery("select u from Users u", Users.class).getResultList();
    }

    public Users findByUsername(String username) {
        try {
            return em.createQuery("select u from Users u where u.username = :username", Users.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void updateLastTestID(Integer userId, int i) {
        begin();
        Users user = findById(Long.valueOf(userId));
        user.setLastTestID(i);
        update(user);
        commit();
    }

    public void updateScore(Integer userId, int i) {
        begin();
        Users users = findById(Long.valueOf(userId));
        users.setScore(i);
        update(users);
        commit();
    }

}
