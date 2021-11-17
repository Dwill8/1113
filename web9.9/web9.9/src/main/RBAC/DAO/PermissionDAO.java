package main.RBAC.DAO;

import java.util.List;

public interface PermissionDAO {
    public List showPermission();
    public boolean addPermission(Integer rid, Integer pid);
    public boolean deletePermission(Integer rid, Integer pid);
}
