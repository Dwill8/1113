package main.RBAC.DAO;

import java.util.List;

public interface RoleDAO {
    public List showRole();
    public void addRole(String username, Integer rid);
    public void deleteRole(String username, Integer rid);
}
