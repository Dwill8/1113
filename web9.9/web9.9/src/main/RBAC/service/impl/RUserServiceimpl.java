package main.RBAC.service.impl;

import main.RBAC.bean.Permission;
import main.RBAC.bean.RUser;
import main.RBAC.service.RUserService;

import java.util.ArrayList;
import java.util.List;

public class RUserServiceimpl implements RUserService {
    @Override
    public List showUser() {
        List<RUser> list = new ArrayList<>();
        //TODO
        return list;
    }

    @Override
    public void addUser(String username) {

    }

    @Override
    public void deleteUser(String username) {

    }
}
