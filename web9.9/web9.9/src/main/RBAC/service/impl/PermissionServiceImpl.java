package main.RBAC.service.impl;

import main.RBAC.bean.Permission;
import main.RBAC.service.PermissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionServiceImpl implements PermissionService {
    @Override
    public List showPermission() {
        List<Permission> list = new ArrayList<>();
        //TODO
        return list;
    }

    @Override
    public void addPermission(Integer rid, Integer pid) {

    }

    @Override
    public void deletePermission(Integer rid, Integer pid) {

    }
}
