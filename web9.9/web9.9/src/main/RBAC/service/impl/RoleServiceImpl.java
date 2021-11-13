package main.RBAC.service.impl;

import main.RBAC.bean.Permission;
import main.RBAC.bean.Role;
import main.RBAC.service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Override
    public List showRole() {
        List<Role> list = new ArrayList<>();
        //TODO
        return list;
    }

    @Override
    public void addRole(String username, Integer rid) {

    }

    @Override
    public void deleteRole(String username, Integer rid) {

    }
}
