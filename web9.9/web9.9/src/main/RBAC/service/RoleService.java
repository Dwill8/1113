package main.RBAC.service;

import java.util.List;

public interface RoleService {
    public List showRole();
    public void addRole(String username, Integer rid);
    public void deleteRole(String username, Integer rid);
}
