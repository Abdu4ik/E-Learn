package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Questions;
import com.tafakkoor.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDAO<T extends BaseEntity, ID extends Serializable> {
    protected final EntityManagerFactory emf;
    protected final EntityManager em;
    private final Class<T> persistenceClass;

    @SuppressWarnings( "unchecked" )
    protected BaseDAO() {
        this.emf = Persistence.createEntityManagerFactory("english-learning-platform");
        this.em = emf.createEntityManager();
        this.persistenceClass = (Class<T>) ( ( (ParameterizedType) getClass()
                .getGenericSuperclass() )
                .getActualTypeArguments()[0] );
    }

    public T save( T t ) {
        begin();
        em.persist(t);
        commit();
        return t;
    }

    public T findById( ID id ) {
        begin();
        T t = em.find(persistenceClass, id);
        commit();
        return t;
    }

    public boolean update( T t ) {
        begin();
        em.merge(t);
        commit();
        return true;
    }

    public boolean delete( T t ) {
        em.remove(t);
        return true;
    }

    public boolean deleteById( ID id ) {
        begin();
        boolean delete = em.createQuery("delete from " + persistenceClass.getSimpleName() + " t where t.id = :id")
                .setParameter("id", id)
                .executeUpdate() == 0;
        commit();
        return delete;
    }

    public boolean changeDeleted(ID id, Boolean deleted) {
        begin();
        int i = em.createQuery("update " + persistenceClass.getSimpleName() + " t set t.deleted = :deleted where t.id = :id")
                .setParameter("deleted", deleted)
                .setParameter("id", id)
                .executeUpdate();
        commit();
        return i > 0;
    }

    public List<T> findAll() {
        begin();
        Query query = em.createQuery("from " + persistenceClass.getSimpleName());
        System.out.println(query.toString());
        commit();
        return query.getResultList();
    }






    protected void begin() {
        em.getTransaction().begin();
    }

    protected void commit() {
        em.getTransaction().commit();
    }
}