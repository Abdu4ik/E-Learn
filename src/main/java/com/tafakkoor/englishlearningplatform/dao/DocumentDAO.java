package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.Document;

public class DocumentDAO extends BaseDAO<Document, Integer> {
    public static DocumentDAO getInstance() {
        return new DocumentDAO();
    }
}
