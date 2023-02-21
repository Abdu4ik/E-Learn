package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Document;

public class DocumentDAO extends BaseDAO<Document, Integer> {
    private static final ThreadLocal<DocumentDAO> documentDAOThreadLocal = ThreadLocal.withInitial(DocumentDAO::new);

    public static DocumentDAO getInstance() {
        return documentDAOThreadLocal.get();
    }
}
