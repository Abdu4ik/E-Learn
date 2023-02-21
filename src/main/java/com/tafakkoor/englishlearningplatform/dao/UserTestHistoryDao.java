package com.tafakkoor.englishlearningplatform.dao;

import com.tafakkoor.englishlearningplatform.domains.UsersTestsHistory;

public class UserTestHistoryDao extends BaseDAO<UsersTestsHistory, Integer> {
    private static final ThreadLocal<UserTestHistoryDao> userTestHistoryDaoThreadLocal = ThreadLocal.withInitial(UserTestHistoryDao::new);

    public static UserTestHistoryDao getInstance() {
        return  userTestHistoryDaoThreadLocal.get();
    }
}
