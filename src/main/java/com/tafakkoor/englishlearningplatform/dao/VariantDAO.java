package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Questions;
import com.tafakkoor.englishlearningplatform.domains.Variants;

import java.util.List;


public class VariantDAO extends BaseDAO<Variants, Integer> {

    private static final ThreadLocal<VariantDAO> variantDAOThreadLocal = ThreadLocal.withInitial(VariantDAO::new);

    public List<Variants> findAllByQuestionId( Integer id ) {
        begin();
        List<Variants> variantsList = em.createNativeQuery("select * from variants where questions_id = :id", Variants.class).setParameter("id", id).getResultList();
        commit();
        return variantsList;
    }

    public static VariantDAO getInstance() {
        return variantDAOThreadLocal.get();
    }

    public void deleteVariantsByQuestionId(Integer id) {
        begin();
        em.createNativeQuery("delete from variants where questions_id = :id").setParameter("id", id).executeUpdate();
        commit();
    }
}
