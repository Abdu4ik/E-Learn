package com.tafakkoor.englishlearningplatform.dao;

import jakarta.persistence.EntityManager;

import java.util.List;

public interface DAO<T> {

    T save(T t);

    T delete(T t);

    T update(T t);

    T find(Integer id);

    List<T> findAll();

}
