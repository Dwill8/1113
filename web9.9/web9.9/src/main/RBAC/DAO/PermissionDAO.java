package main.RBAC.DAO;

import java.util.List;

public interface PermissionDAO {
    public List showPermission();
    public void addPermission(Integer rid, Integer pid);
    public void deletePermission(Integer rid, Integer pid);
}
