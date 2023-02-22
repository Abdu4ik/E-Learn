package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Questions;
import com.tafakkoor.englishlearningplatform.domains.Variants;

import java.util.List;


public class VariantDAO extends BaseDAO<Variants, Integer> {


    public List<Variants> findAllByQuestionId( Integer id ) {
        begin();
        List<Variants> variantsList = em.createNativeQuery("select * from variants where questions_id = :id", Variants.class).setParameter("id", id).getResultList();
        commit();
        return variantsList;
    }

    public static VariantDAO getInstance() {
        return new VariantDAO();
    }
}
