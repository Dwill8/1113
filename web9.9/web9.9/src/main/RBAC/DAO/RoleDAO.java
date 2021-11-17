package main.RBAC.DAO;

import java.util.List;

public interface RoleDAO {
    public List showRole();
    public boolean addRole(Integer uid, Integer rid);
    public boolean deleteRole(Integer uid, Integer rid);
}
