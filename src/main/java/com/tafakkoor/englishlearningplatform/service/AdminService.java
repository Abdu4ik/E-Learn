package com.tafakkoor.englishlearningplatform.service;

import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import com.tafakkoor.englishlearningplatform.domains.Users;

import java.util.List;

public class AdminService {


    private static final AdminService service = ThreadLocal.withInitial(AdminService::new).get();


    public static AdminService getInstance() {
        return service;
    }

    public  List<Users> usersList(Integer page, Integer size) {
        // todo check page and size
        List<Users> users = new UserDAO().getPage(page, size);
        return users;
    }


    public boolean changeRole(Integer id, String role) {
        //todo check role is valid
        boolean result = new UserDAO().changeRole(id, role);
        return result;
    }

    public boolean changeDeleted(Integer userId, boolean deleted) {

        //todo check userId  is valid
        boolean result = new UserDAO().changeDeleted(Long.valueOf(userId), deleted);
        return result;
    }
}